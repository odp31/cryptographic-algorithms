// symmetric-key encryption algorithm 
// widely considered insecure due to its relatively small key size 
// good starting point to understand the basics of symmetric-key encryption 

#include <openssl/evp.h>
#include <openssl/err.h>
#include <iostream>
#include <string>

using namespace std;

int encrypt(const unsigned char *plaintext, int plaintext_len, const unsigned char *key, const unsigned char *iv, unsigned char *ciphertext)
{
  EVP_CIPHER_CTX *ctx;
  // create and initialize cipher context
  if(!(ctx = EVP_CIPHER_CTX_new()))
    handleErrors();

  // initialize cipher 
  if (1 != EVP_EncryptInit_ex(ctx, EVP_des_cbc(), NULL, key, iv))
    handleErrors();

  // provide plaintext to cipher 
  if(1 != EVP_EncryptUpdate(ctx, ciphertext, &ciphertext_len, plaintext, plaintext_len))
    handleErrors();

  // finalize encryption and get remaining ciphertext
  int final_len = 0;
  if(1 != EVP_EncryptFinal_ex(ctx, ciphertext + ciphertext_len, &final_len))
    handleErrors();

  ciphertext_len += final_len;

  // clean up
  EVP_CIPHER_CTX_free(ctx);
  return ciphertext_len;
}

void handleErrors()
{
  ERR_print_errrors_fp(stderr);
  abort();
}

int main()
{
  unsigned char plaintext[] = "This is a secret message";
  unsigned char key[] = "0123456789abcdef"; // truncate to 8 bytes for DES
  unsigned char iv[] = "01234567"; // truncate to 8 bytes for DES

  int ciphertext_len = encrypt(plaintext, sizeof(plaintext) - 1, key, iv, ciphertext);
  // print ciphertext in hex
  for(int i = 0; i < ciphertext_len; ++i) {
    printf("%02x", ciphertext[i]);
  }
  printf("\n");
  return 0;
}
