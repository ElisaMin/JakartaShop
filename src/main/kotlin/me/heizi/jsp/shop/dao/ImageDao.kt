package me.heizi.jsp.shop.dao

import me.heizi.jsp.shop.entities.Image
import java.io.InputStream

/** */

interface ImageDao {
    /** 添加图片*/
    fun insertImage(inputStream: InputStream, mime:String)
    /** 获取图片,通过ID */
    fun getImageByID(id:Int): Image

}