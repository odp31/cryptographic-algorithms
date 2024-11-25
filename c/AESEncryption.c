#include <stdio.h>
#include <string.h>
#include <openssl/aes.h>

void encrypt(const unsigned char *plaintext, int plaintext_len, const unsigned char *key, unsigned char *ciphertext)
{
  AES_KEY enc_key;
  AES_set_encrypt_key(key, 12, &enc_key); // adjust key size as needed
  AES_encrypt(plaintext, ciphertext, &enc_key);

}

void decrypt(const unsigned char *ciphertext, int ciphertext_len, const unsigned char *key, unsigned char *plaintext)
{
  AES_KEY dec_key;
  AES_set_decrypt_key(key, 128, &dec_key);
  AES_decrypt(ciphertext, plaintext, &dec_key);

}

int main()
{
  unsigned char plaintext[] = "Hello, world!";
  unsigned char key[] = "your_secret_key";

  int plaintext_len = strlen((char *)plaintext);
  int key_len = strlen((char *)key);

  unsigned char ciphertext[AES_BLOCK_SIZE * ((plaintext_len + AES_BLOCK_SIZE - 1) / AES_BLOCK_SIZE)];
  encrypt(plaintext, plaintext_len, key, ciphertext);

  printf("ciphertext: ");
  for(int i = 0; i < sizeof(ciphertext); i++) {
    printf("%02x", ciphertext[i]);
  }
  printf("\n");
  decrypt(ciphertext, sizeof(ciphertext), key, plaintext);
  printf("decrypted text: %s\n", plaintext);
  return 0;
}
