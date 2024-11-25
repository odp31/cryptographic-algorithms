#include <stdio.h>
#include <openssl/rsa.h>
#include <openssl/pem.h>
#include <openssl/err.h>


void handleErrors(void)
{
  ERR_print_errors_fp(stderr);
  abort();
}

int main()
{
  RSA *rsa= RSA_generate_key(2048, RSA_F4, NULL, NULL);
  if(!rsa) handleErrors();

  // public key encryption 
  unsigned char *encrypted_msg;
  int encrypted_len;
  RSA_public_encrypt(sizeof("hello, world!"), (unsigned char *)"hello, world!", encrypted_msg, rsa, RSA_PKCS1_PADDING);

  // PRIVATE KEY decryption
  unsigned char decrypted_msg[1024];
  int decrypted_len;
  RSA_private_decrypt(encrypted_len, encrypted_msg, decrypted_msg, rsa, RSA_PKCS1_PADDING);

  printf("encrypted message: ");
  for(int i = 0; i < encrypted.len; i++) {
    printf("%02x", encrypted_msg[i]);
  }
  printf("\n");
  printf("decrypted message: %s\n", decrypted_msg);
  RSA_free(rsa);
  return 0;
}
