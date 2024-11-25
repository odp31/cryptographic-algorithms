import random

def gcd(a, b):
  while b != 0:
    a, b = b, a % b
  return a 

def generate_keypair(p, q):
  n = p * q
  phi = (p - 1) * (q - 1)

  # choose an integer e such that 1 < e < phi(n) and gcd(e, phi(n)) = 1
  e = random.randrange(1, phi)
  while gcd(e, phi) != 1:
    e = random.randrange(1, phi)

  # use extended euclidean to find modular mulitplicative inverse of e mod phi(n)
  d = pow(e, -1, phi)

  return ((e, n), (d, n))

def encrypt(pk, plaintext):
  key, n = pk
  cipher = [(ord(char) ** key) % n) for char in ciphertext]
  return ''.join(plain)

# example usage
p = 17
q = 11

public, private = generate_keypair(p, q)

message = "Hello, world!"
encrypted_msg = encrypt(public, message)
print("encrypted message:", encrypted_msg)

decrypted_msg = decrypt(private, encrypted_msg)
print("decrypted message;", decrypted_msg) 
