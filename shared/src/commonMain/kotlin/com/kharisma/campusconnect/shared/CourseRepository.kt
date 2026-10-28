package com.kharisma.campusconnect.shared

interface CourseRepository {
    fun getAllCourses(): List<MataKuliah>
}

class CourseRepositoryImpl : CourseRepository {
    override fun getAllCourses(): List<MataKuliah> = listOf(
        MataKuliah(1, "Pemrograman Mobile 2", 3, "Syaiful Rahman"),
        MataKuliah(2, "Basis Data Lanjut", 3, "Andi Wijaya"),
        MataKuliah(3, "Kecerdasan Buatan", 3, "Nur Aisyah"),
        MataKuliah(4, "Rekayasa Perangkat Lunak", 3, "Budi Santoso"),
        MataKuliah(5, "Jaringan Komputer", 2, "Citra Lestari"),
        // Langkah 6: mata kuliah baru untuk uji konsistensi data lintas platform
        MataKuliah(6, "Sistem Operasi", 3, "Dewi Anggraini")
    )
}
