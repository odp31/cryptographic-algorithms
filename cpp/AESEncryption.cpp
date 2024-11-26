// symmetric key encryption standard widely used for secure data transmission
// involves a series of transformations on plaintext to produce ciphertext

// openSSL is a powerful cryptographic library that provides a C++ API to implement AES encryption

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
  if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv))
    handleErrors();
  // provide plaintext to cipher 
  if (1 != EVP_EncryptUpdate(ctx, ciphertext, &ciphertext_len, plaintext, plaintext_len))
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
  ERR_print_errors_fp(stderr);
  abort();
}

int main()
{
  unsigned char plaintext[] = "This is a secret message";
  unsigned char key[] = "0123456789abcdef0123456789abcdef";
  unsigned char iv[] = "0123456789abcdef";
  int ciphertext_len = encrypt(plaintext, sizeof(plaintext) - 1, key, iv, ciphertext);
  // print ciphertext in hexadecimal format
  for(int i = 0; i < ciphertext_len; i++) {
    printf("%02x", ciphertext[i]);
  }
  printf("\n");
  return 0;
}
