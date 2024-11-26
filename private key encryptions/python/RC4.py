# DEEMED INSECURE

def rc4_crypt(data, key):
  S = list(range(256))
  j = 0
  for i in range(256):
    j = (j + S[i] + ord(key[i % len(key)])) % 256
    S[i], S[j] = S[j], S[i]
  i = j = 0
  out = []
  for char in data:
    i = (i + 1) % 256
    j = (j + S[i]) % 256
    S[i], S[j] = S[j], S[i]
    K = S[(S[i] + S[j]) % 256]
    out.append(chr(ord(char) ^ K))

  return ''.joint(out)

# example usage
plaintext = "hello world"
key = "secretkey"

ciphertext = rc4_crypt(plaintext, key)
decrypted_text = rc4_crypt(ciphertext, key)

print("cipher text: ", ciphertext)
print("decrypted text:", decrypted_text)
