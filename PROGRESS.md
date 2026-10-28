# PROGRESS — CampusConnect

Mata Kuliah: Pemrograman Mobile 2 (23525933) — STMIK Kharisma Makassar
NIM: 52024351

---

## Pertemuan 2 — Kotlin Multiplatform (KMP) & Compose Multiplatform

### Ringkasan
Logika data mata kuliah dipindahkan dari modul `:app` (Android) ke modul baru `:shared`
(KMP) sehingga dapat dipakai lintas platform. Modul `:desktopApp` ditambahkan sebagai
bukti bahwa kode yang sama dikonsumsi oleh target Desktop.

### Struktur modul

```
CampusConnect
├── app/              -> UI Android (Jetpack Compose), bergantung ke :shared
├── shared/           -> Modul KMP (target: Android + Desktop/JVM)
│   └── src/
│       ├── commonMain/   MataKuliah, CourseRepository(+Impl), expect getPlatformName()
│       ├── androidMain/  actual getPlatformName() = "Android"
│       └── desktopMain/  actual getPlatformName() = "Desktop/JVM"
└── desktopApp/       -> Aplikasi Compose Desktop (Main.kt), bergantung ke :shared
```

### Perubahan yang dilakukan
| Langkah | Perubahan |
|---|---|
| 1 | Modul `:shared` dibuat; target Desktop ditambahkan lewat `jvm("desktop")`; target iOS tidak dikonfigurasi |
| 2 | `MataKuliah.kt` dipindah ke `commonMain`; dibuat `CourseRepository` + `CourseRepositoryImpl`; `DummyData.kt` dihapus dari `:app` |
| 3 | `expect fun getPlatformName()` di `commonMain` + `actual` di `androidMain` dan `desktopMain` |
| 4 | `implementation(project(":shared"))` pada `app/build.gradle.kts`; `MainActivity` memakai `CourseRepositoryImpl()` |
| 5 | Modul `:desktopApp` + `Main.kt` (Compose Desktop) |
| 6 | Ditambahkan `MataKuliah(6, "Sistem Operasi", 3, "Dewi Anggraini")` di `CourseRepositoryImpl` saja |

### Catatan teknis (penyesuaian terhadap versi tooling proyek)
Proyek ini memakai **AGP 9.4.1**. Sejak AGP 9, plugin `com.android.library` tidak lagi
kompatibel dengan plugin `kotlin.multiplatform`, sehingga modul `:shared` memakai plugin
`com.android.kotlin.multiplatform.library` dengan blok `kotlin { android { ... } }`
(bukan `androidTarget()`). Versi Kotlin dinaikkan ke 2.2.20 agar selaras dengan
Compose Multiplatform 1.9.0.

### Cara menjalankan
- **Android:** pilih konfigurasi `app` lalu klik ▶ Run.
- **Desktop:** `./gradlew :desktopApp:run`

### Bukti Eksekusi

**Android** (HomeScreen menampilkan 6 mata kuliah dari `:shared`)

![Android](docs/screenshots/android.png)

**Desktop** (jendela "CampusConnect Desktop", `Berjalan di: Desktop/JVM`)

![Desktop](docs/screenshots/desktop.png)

### Verifikasi Konsistensi Data (Langkah 6)
Mata kuliah baru hanya ditambahkan pada `CourseRepositoryImpl.kt` di modul `:shared`.
Tanpa mengubah kode `:app` maupun `:desktopApp`, mata kuliah **Sistem Operasi** muncul di
kedua target. Ini membuktikan `:shared` adalah *single source of truth*.

<img width="1919" height="1034" alt="image" src="https://github.com/user-attachments/assets/d8498d1c-02bd-427c-9172-d6fdb53b510f" />
