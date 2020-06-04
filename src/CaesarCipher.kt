// A program to encrypt and decrypt a string using simple Caesar Cipher in Kotlin

import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.*

class CaesarCipher {
    private val charMap: HashMap<Char, Int> = HashMap()

    //Returns an integer that is between 0 - 25 (a-z).
    fun generateKey(): Int {
        var secureRandom = SecureRandom()
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return secureRandom.nextInt(26)
    }


    /*
      Encrypts using the formula: (m(i) + k) mod 26.
      Returns cipher text or null (if a error occurred).
     */
    fun encrypt(plainText: String, key: Int): String? {
        var plainText = plainText
        var encryptedText = ""
        //Make sure the key is valid.
        if (key < 0 || key > 25 || plainText.isEmpty()) {
            return null
        }

        //Eliminates any whitespace and non alphabetic characters and converting to uppercase
        plainText = plainText.filter { it.isLetter() }.toUpperCase()

        for (element in plainText) {
            val lookUp = (charMap[element]!! + key) % 26
            encryptedText += encryptionArr[lookUp]
        }
        return encryptedText
    }


    /*
      Decrypts using the formula: (c(i) – k) mod 26.
      Returns plain text or null (if a error occurred).
     */
    fun decrypt(cipherText: String, key: Int): String? {
        var cipherText = cipherText
        var decryptedText = ""
        //Make sure the key is valid.
        if (key < 0 || key > 25) {
            return null
        }
        //Eliminates any whitespace and non alphabetic characters and converting to uppercase
        cipherText = cipherText.filter { it.isLetter() }.toUpperCase()

        for (element in cipherText) {
            var lookUp = (charMap[element]!! - key) % 26
            //Returns a positive number on negative input.
            if (lookUp < 0) {
                lookUp += 26
            }
            decryptedText += encryptionArr[lookUp]
        }
        return decryptedText
    }

    companion object {

        private val encryptionArr = charArrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')
    }

    init {
        charMap['A'] = 0
        charMap['B'] = 1
        charMap['C'] = 2
        charMap['D'] = 3
        charMap['E'] = 4
        charMap['F'] = 5
        charMap['G'] = 6
        charMap['H'] = 7
        charMap['I'] = 8
        charMap['J'] = 9
        charMap['K'] = 10
        charMap['L'] = 11
        charMap['M'] = 12
        charMap['N'] = 13
        charMap['O'] = 14
        charMap['P'] = 15
        charMap['Q'] = 16
        charMap['R'] = 17
        charMap['S'] = 18
        charMap['T'] = 19
        charMap['U'] = 20
        charMap['V'] = 21
        charMap['W'] = 22
        charMap['X'] = 23
        charMap['Y'] = 24
        charMap['Z'] = 25
    }
}

fun main() {

    var encryptor: CaesarCipher = CaesarCipher()                              //An object of class CaesarCipher is created

    val key:Int = encryptor.generateKey()

    val encryptedValue: String? =encryptor.encrypt("password@!9 n", key)
    println("Encrypted Value : $encryptedValue")

    val decryptedValue: String? =encryptor.decrypt(encryptedValue!!, key)
    println("Decrypted Value : $decryptedValue")                               //Returns the original text which is stripped of numbers and special characters
}
