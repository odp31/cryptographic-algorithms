from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes 

def encrypt_des(plaintext, key):
  ciper = Cipher.new(algorithms.DES(key), modes.ECB())
  ciphertext = cipher.encrypt(plaintext)
  return ciphertext 

def decrypt_des(ciphertext, key):
  cipher = Cipher.new(algorithms.DES(key), modes.ECB())
  plaintext = cipher.decrypt(ciphertext)
  return plaintext

# example usage
plaintext = b"Hellow, world!"
key = b'secretkey' # 8 byte key

ciphertext = encrypt_des(plaintext, key)
decrypted_plaintext = decrypt_des(ciphertext, key)

print(decrypted_plaintext)
