# AES

這是很一般的 [AES](https://www.wikiwand.com/zh/%E9%AB%98%E7%BA%A7%E5%8A%A0%E5%AF%86%E6%A0%87%E5%87%86) 加解密

會寫這個是實習時主管有些相關想法，先做一般的加解密再做深入研究

<h2 id = "intro">Introduction</h2>

痾，做個簡單的介紹好了...

總之，在**密碼學(Cryptography)**中，他是**對稱式加密**，又稱**Rijndael加密**(我不會念這個字)

對稱式加密，就是只有**一把金鑰(key)**，加解密都用同一把，速度快

加密時會先把明文(plaintext)轉成 byte

加密後再依個人需求轉成想要的密文(ciphertext)格式

進行加密時，會依需求使用不同的 [Block Cipher Mode](https://www.wikiwand.com/en/Block_cipher_mode_of_operation#) 提高安全性

<h2 id = "aes256">AES-256</h2>

使用一般 Java 內建函式只能做到 AES-128

如果想用 AES-256，需要額外 import [Java Cryptography Extension (JCE)](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)

<h2 id = "EncryptMethod">EncryptMethod</h2>

密文可以以 16 進位或是 ASCII 格式呈現

<h2 id = "Generate">Generate</h2>

Secret Key 跟 IV (Initial Vector) 每次都是隨機產生的