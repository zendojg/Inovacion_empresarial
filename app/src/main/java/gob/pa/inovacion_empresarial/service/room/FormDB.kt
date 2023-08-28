package gob.pa.inovacion_empresarial.service.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    DBform::class,//------
    DBprovincia::class,
    DBdistritos::class,
    DBcorregimiento::class,
    DBlugarP::class],
    version = 1)

abstract class FormDB: RoomDatabase() {
    abstract fun dbFormDao(): FormDao
    companion object {
        @Volatile private var InternalDB: FormDB? = null
        fun getInstance(context: Context): FormDB = InternalDB ?: synchronized(this) {
                InternalDB ?: buildDB(context).also { InternalDB = it }
            }
        fun buildDB(context: Context) =
            Room.databaseBuilder(context.applicationContext, FormDB::class.java, "DB_test1.db")
                .fallbackToDestructiveMigration() // Borra DB al actualizar version
                //.addMigrations()
                .build()
    }

}