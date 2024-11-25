#include <stdio.h>
#include <string.h>
#include <openssl/des.h>

void encrypt_des(const unsigned char *plaintext, int plaintext_len, const unsigned char *key, unsigned char *ciphertext)
{
  DES_cblock key_schedule;
  DES_key_schedule((const_DES_CBLOCK *)key, &key_schedule);

  DES_cblock in, out;
  for(int i = 0; i < plaintext_len; i+= 8) {
    memcpy(in, plaintext + i, 8);
    DES_ecb_encrypt(&in, &out, &key_schedule, DES_ENCRYPT);
    memcpy(ciphertext + i, out, 8);
  }
}

void decrypt_des(const unsigned char *ciphertext, int ciphertext_len, const unsigned char *key, unsigned char *plaintext)
{
  DES_cblock key_schedule;
  DES_key_schedule((const_DES_cblock *)key, &key_schedule);

  DES_cblock in, out;
  for(int i = 0; i < ciphertext_len; i+= 8) {
    memcpy(in, ciphertext + i, 8);
    DES_ecb_encrypt(&in, &out, &key_schedule, DES_DECRYPT);
    memcpy(plaintext + i, out, 8);
  }
}

int main()
{
  unsigned char plaintext[] = "Hello world";
  unsigned char key[] = "secretKey"; // 8 byte key
  int plaintext_len = strlen((char *) plaintext);
  int key_len = strlen((char *) key);

  unsigned char ciphertext[8 * ((plaintext_len + 7) / 8)];
  encrypt_des(plaintext, plaintext_len, key, ciphertext);

  printf("ciphertext: ");
  for(int i = 0; i < sizeof(ciphertext); i++) {
    printf("%02x", ciphertext[i]);
  }
  printf("\n");
  decrypt_des(ciphertext, sizeof(ciphertext), key, plaintext);
  printf("decrypted text: %s\n", plaintext);

  return 0;
}
