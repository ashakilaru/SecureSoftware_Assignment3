#include <iostream>
#include <cstdlib>
#include <ctime>
#include <string>

// Generate a random key of given length
std::string generateKey(int length) {
    std::string key = "";
    for (int i = 0; i < length; ++i) {
        key += static_cast<char>(rand() % 256);
    }
    return key;
}

// Encrypt message using XOR with key
std::string encryptOTP(const std::string& message, const std::string& key) {
    std::string encrypted = "";
    for (size_t i = 0; i < message.size(); ++i) {
        encrypted += static_cast<char>(message[i] ^ key[i]);
    }
    return encrypted;
}

// Decrypt message (same as encryption in OTP)
std::string decryptOTP(const std::string& encrypted, const std::string& key) {
    std::string decrypted = "";
    for (size_t i = 0; i < encrypted.size(); ++i) {
        decrypted += static_cast<char>(encrypted[i] ^ key[i]);
    }
    return decrypted;
}

int main() {
    srand(static_cast<unsigned>(time(0)));

    std::string message = "MY NAME IS UNKNOWN";
    std::string key = generateKey(message.length());

    std::string encrypted = encryptOTP(message, key);
    std::string decrypted = decryptOTP(encrypted, key);

    std::cout << "Original Message: " << message << "\n";
    std::cout << "Encrypted (raw bytes): ";
    for (unsigned char c : encrypted) std::cout << std::hex << (int)c << " ";
    std::cout << "\nDecrypted Message: " << decrypted << "\n";

    return 0;
}
