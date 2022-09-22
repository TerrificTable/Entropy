package xyz.terrifictable.entropy.data

import xyz.terrifictable.entropy.utils.Logger
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*


class DB {


    private fun connMariaDB(url: String, connConfig: Properties): Connection {
        Class.forName("org.mariadb.jdbc.Driver").newInstance()
        return DriverManager.getConnection(url, connConfig)
    }
    private fun connMySQL(url: String, connConfig: Properties): Connection {
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        return DriverManager.getConnection(url, connConfig)
    }




    fun connect(url: String, user: String, password: String): Connection? {
        try{

            val connConfig = Properties()
            connConfig.setProperty("user",      user)
            connConfig.setProperty("password",  password)

            val conn: Connection?
            if (url.contains("mariadb"))
                conn = connMariaDB(url, connConfig)
            else if (url.contains("mysql"))
                conn = connMySQL(url, connConfig)
            else {
                Logger.error("Failed to connect to '$url'", "DB_Connect")
                return null
            }

            Logger.success("Successfully connected to: '${url}' as '${user}'", "DB_Connect")
            return conn

        } catch (e: SQLException){
            Logger.error("Failed to connect to '$url': " + e.message, "DB_Connect")
            return null
        }
    }

    fun disconnect(conn: Connection): Boolean {
        try {
            conn.close()
        } catch (e: Exception) {
            return false
        }
        return conn.isClosed
    }

}