// asymmetric key cryptography algorithm 
// uses a pair of keys; public for encryption and private for decryption 

#include <openssl/rsa.h>
#include <openssl/pem.h>
#include <openssl/err.h>
#include <iostream>
#include <string>

using namespace std;

void handleErrors()
{
  ERR_print_errors_fp(stderr);
  abort();
}

int main()
{
  // generate an RSA key pair
  RSA *rsa = RSA_generate_key(2048, RSA_F4, NULL, NULL);
  if (!rsa) handleErrors();
  // write the public key to a file
  FILE *pubfile = fopen("public_key.pem", "w");
  PEM_write_RSA_PUBKEY(pubfile, rsa);
  fclose(pubfile);

  // encrypt a message using the public key 
  string messsage = "Hello, world!";
  unsigned char *encrypted_msg;
  int encrypted_len;

  if (!RSA_public_encrypt(message.length(), (unsigned char*)message.c_str(), &encrypted_msg, rsa, RSA_PKCS1_PADDING)) {
    handleErrors();
  }
  // print encrypted message (in hexadecimal format)
  for(int i = 0; i < encrypted_len; ++i) {
    printf("%02x", encrypted_msg[i]);
  }
  printf("\n");
  // free encrypted message and RSA key
  RSA_free(rsa);
  OPENSSL_free(encrypted_msg);
  return 0;
}
    
