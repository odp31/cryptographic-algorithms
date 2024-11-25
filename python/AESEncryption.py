from cryptography.fernet import Fernet

def generate_key():
  key = Fernet.generate_key()
  return key

def encrypt_message(message, key):
  f = Fernet(key)
  encrypted_message = f.encrypt(message.encode())
  return encrypted_message

def decrypt_message(encrypted_message, key):
  f = Fernet(key)
  decrypted_message = f.decrypt(encrypted_message).decode()
  return decrypted_message

# example usage
key = generate_key()
message = "This is a secret message"

encrypted_message = encrypt_message(message, key)
print("encrypted message:", encrypted_message)

decrypted_message = decrypt_message(encrypted_message, key)
print("decrypted message:", decrypted_message)

