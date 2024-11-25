# older encryption standard
# data encryption standard 
# no longer considered secure for most modern applications due to its relatively smalll key size 

from Crypto.Cipher import DES
from Crypto.Util.Padding import pad, unpad 

def encrypt_des(message, key):
  des = DES.new(key, DES.MODE_ECB)
  padded_message = pad(message.encode(), DES.block_size)
  encrypted_message = des.encrypt(padded_message)
  return encrypted_message

def decrypt_des(encrypted_message, key):
  des = DES.new(key, DES.MODE_ECB)
  decrypted_message = des.decrypt(encrypted_message)
  return unpad(decrypted_message, DES.block_size).decode()


# example usage
key = b'secretkey' 
message = "hello, world!"

encrypted_message = encrypt_des(message, key)
print("encrypted message: ", encrypted_message)

decrypted_message = decrypt_des(encrypted_message, key)
print("decrypted message:", decrypted_message)

