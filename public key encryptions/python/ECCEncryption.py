# elliptic curve cryptography 

from cryptography.hazmat.primitives.asymmetric import ec
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import serialization

# generate an ECC key pair
private_key = ec.generate_private_key(
  curve=ec.SECP256R1(),
  backend=default_backend()
)
public_key = private_key.public_key()

# serialize the public key for sharing
pem = public_key.public_bytes(
  encoding=serialization.Encoding.PEM,
  format=serialization.PublicFormat.SubjectPublicKeyInfo
)

# encrypt a message using a hybrid scheme
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.kdf.hkdf import HKDF
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes

def encrypt(plaintext, public_key):
  # generate random symmetric key
  symmetric_key = os.urandom(32)

  # derive a shared secret using ECDH
  shared_key = private_key.exchange(ec.ECDH(), public_key)
  derived_key = HKDF(
    algorithm=hashes.SHA256(),
    length=32, 
    salt=None,
    info=b'encryption'
  ).derive(shared_key)

#encrypt symmetric key using public key
  ciphertext_key = public_key.encrypt(
    symmetric_key,
    padding.OAEP(
      mgf=padding.MGF1(algorithm=hashes.SHA256()),
      algorithm=hashes.SHA256(),
      label=None
    )
  )
  # encrypt message using symmetric key 
  cipher = Cipher(algorithms.AES(derived_key), modes.CBC(), backend=default_backend())
  encryptor = cipher.encryptor()
  iv = os.urandom(16)
  ciphertext = encryptor.update(plaintext) + encryptor.finalize()

  return ciphertext_key, iv, ciphertext 

# decrypt message
def decrypt(ciphertext_key, iv, ciphertext, private_key):
  # decrypt symmetric key
  symmetric_key = private_key.decrypt(
    ciphertext_key,
    padding.OAEP(
      mgf=padding.MGF1(algorithm=hashes.SHA256()),
      algorithm=hashes.SHA256(),
      label=None
    )
  )
  # derive shared secret
  shared_key = private_key.exchange(ec.ECDH(), public_key)
  derived_key = HKDF(
    algorithm=hashes.SHA256(),
    length=32, 
    salt=None,
    info=b'encryption'
  ).derive(shared_key)

# decrypt message
cipher = Cipher(algorithms.AES(derived_key), modes.CBC(), backend=default_backend())
decryptor = cipher.decryptor()
plaintext = decryptor.update(ciphertext) + decryptor.finalize()

return plaintext

# example usage
message = b"Hello, world!"
ciphertext_key, iv, ciphertext = encrypt(message, public_key)
plaintext = decrypt(ciphertext_key, iv, ciphertext, private_key)
print(plaintext) 
