# Catatan Analisis Arsitektur - CampusConnect

## 1. Alasan Pemilihan Pendekatan Native (Kotlin + Jetpack Compose)
Pada tahap awal ini, CampusConnect dibangun menggunakan pendekatan native (Kotlin + Jetpack Compose) karena:
* **Performa & Akses API Maksimal:** Berjalan langsung di atas Android Runtime (ART) tanpa lapisan abstraksi/bridge tambahan, memberikan akses penuh ke fitur OS dan efisiensi memori terbaik.
* **Paradigma UI Deklaratif:** Jetpack Compose mempermudah pembaruan tampilan secara otomatis (*recomposition*) berdasarkan perubahan data (*state*) tanpa manipulasi hirarki View secara manual.
* **Persiapan Arsitektur Long-term:** Pemisahan struktur paket `data` dan `ui` mempermudah transisi ke pola MVVM dan integrasi modul Kotlin Multiplatform (KMP) pada pertemuan selanjutnya.

## 2. Konsekuensi Teknis & Biaya Jika Dirilis untuk iOS
Apabila di masa depan CampusConnect harus dirilis untuk platform iOS:
* **Konsekuensi Teknis (Dua UI Codebase):** Kode UI berbasis Jetpack Compose tidak dapat dipakai langsung di iOS. Lapisan antarmuka pengguna iOS harus ditulis ulang menggunakan **SwiftUI** (atau menggunakan Compose Multiplatform pada modul UI).
* **Pemanfaatan KMP:** Logika bisnis, data model, dan akses jaringan/database yang berada di paket `data` dapat dipindahkan ke modul `commonMain` KMP untuk dipakai bersama oleh Android dan iOS.
* **Konsekuensi Biaya & SDM:** Perlu alokasi tim dengan keahlian Swift/iOS, infrastruktur kompilasi khusus (perangkat macOS dan akun Apple Developer Program), serta alur pengujian (*testing*) dan *CI/CD* terpisah untuk dua aplikasi.