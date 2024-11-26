from cryptography.fernet import Fernet

def encrypt(message, key):
  key = Fernet.generate_key()
  f = Fernet(key)
  encrypted_message = f.encrypt(message.encode())
  return encrypted_message

def decrypt(encrypted_message, key):
  f = Fernet(key)
  decrypted_message = f.decrypt(encrypted_message)
  return decrypted_message.decode()

# example usage
message = "hello world"
key = Fernet.generate_key()

encrypted_message = encrypt(message, key)
decrypted_message = decrypt(encrypted_message, key)

print("encrytped message:", encrypted_message)
print("decrypted message:", decrypted_message) 
