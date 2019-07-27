package com.rahmanarifofficial.mypik_pusatinformasikampus.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FavoriteDbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {
    companion object {
        private var instance: FavoriteDbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteDbHelper {
            if (instance == null) {
                instance = FavoriteDbHelper(ctx.applicationContext)
            }
            return instance as FavoriteDbHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            KampusDB.TABLE_KAMPUS, true,
            KampusDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            KampusDB.KODE to TEXT + UNIQUE,
            KampusDB.NAMA to TEXT,
            KampusDB.LINK_LOGO to TEXT,
            KampusDB.AKREDITASI to TEXT,
            KampusDB.LINK_PMB to TEXT
        )
        db?.createTable(
            JurusanDB.TABLE_JURUSAN, true,
            JurusanDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            JurusanDB.ID_JURUSAN to TEXT + UNIQUE,
            JurusanDB.JURUSAN to TEXT,
            JurusanDB.NAMA_KELOMPOK to TEXT,
            JurusanDB.MAPEL to TEXT,
            JurusanDB.TIPE to TEXT
        )
        db?.createTable(
            BeasiswaDB.TABLE_BEASISWA, true,
            BeasiswaDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            BeasiswaDB.ID_BEASISWA to TEXT + UNIQUE,
            BeasiswaDB.BEASISWA to TEXT,
            BeasiswaDB.DEADLINE to TEXT,
            BeasiswaDB.KATEGORI to TEXT,
            BeasiswaDB.JENIS_PEMBIAYAAN to TEXT,
            BeasiswaDB.PENYELENGGARA to TEXT,
            BeasiswaDB.LINK_BANNER to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(KampusDB.TABLE_KAMPUS, true)
        db?.dropTable(JurusanDB.TABLE_JURUSAN, true)
        db?.dropTable(BeasiswaDB.TABLE_BEASISWA, true)
    }

}

val Context.database: FavoriteDbHelper
    get() = FavoriteDbHelper.getInstance(applicationContext)