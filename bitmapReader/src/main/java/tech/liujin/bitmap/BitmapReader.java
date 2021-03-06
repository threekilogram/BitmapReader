package tech.liujin.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.support.annotation.DrawableRes;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/**
 * 按照配置读取bitmap
 *
 * @author wuxio
 */
public class BitmapReader {

      /**
       * 读取原图,使用RGB_565格式
       *
       * @param context context
       * @param id 资源ID
       *
       * @return 资源对应bitmap
       */
      public static Bitmap read ( Context context, @DrawableRes int id ) {

            return read( context, id, Config.RGB_565 );
      }

      /**
       * 读取原图
       *
       * @param context context
       * @param id 资源ID
       *
       * @return 资源对应bitmap
       */
      public static Bitmap read ( Context context, @DrawableRes int id, Config config ) {

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = config;

            return BitmapFactory.decodeResource( context.getResources(), id, options );
      }

      /**
       * 读取原图,使用RGB_565格式
       *
       * @param file bitmap file
       *
       * @return 资源对应bitmap
       */
      public static Bitmap read ( File file ) {

            return read( file.getAbsolutePath() );
      }

      /**
       * 读取原图
       *
       * @param file bitmap file
       *
       * @return 资源对应bitmap
       */
      public static Bitmap read ( File file, Config config ) {

            return read( file.getAbsolutePath(), config );
      }

      /**
       * 读取原图,使用RGB_565格式
       *
       * @param path :file path
       *
       * @return 资源对应bitmap
       */
      public static Bitmap read ( String path ) {

            return read( path, Config.RGB_565 );
      }

      /**
       * 读取原图
       *
       * @param path :file path
       *
       * @return 资源对应bitmap
       */
      public static Bitmap read ( String path, Config config ) {

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = config;

            return BitmapFactory.decodeFile( path, options );
      }

      /**
       * 读取原图,使用RGB_565格式
       *
       * @param fileDescriptor :fileDescriptor
       *
       * @return 资源对应bitmap
       */
      public static Bitmap read ( FileDescriptor fileDescriptor ) {

            return read( fileDescriptor, Config.RGB_565 );
      }

      /**
       * 读取原图
       *
       * @param fileDescriptor :fileDescriptor
       *
       * @return 资源对应bitmap
       */
      public static Bitmap read ( FileDescriptor fileDescriptor, Config config ) {

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = config;

            return BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );
      }

      /**
       * 读取原图,使用RGB_565格式
       *
       * @param stream stream
       *
       * @return 资源对应bitmap
       */
      public static Bitmap read ( InputStream stream ) {

            return read( stream, Config.RGB_565 );
      }

      /**
       * 读取原图
       *
       * @param stream stream
       *
       * @return 资源对应bitmap
       */
      public static Bitmap read ( InputStream stream, Config config ) {

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = config;

            return BitmapFactory.decodeStream( stream, null, options );
      }

      /**
       * 计算宽高比
       *
       * @param context context
       * @param resId resource id
       *
       * @return 图片宽高比
       */
      public static float calculateAspectRatio ( Context context, int resId ) {
            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource( context.getResources(), resId, options );
            return options.outWidth * 1f / options.outHeight;
      }

      /**
       * 计算宽高比
       *
       * @param file bitmap file
       *
       * @return 图片宽高比
       */
      public static float calculateAspectRatio ( File file ) {
            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            return calculateAspectRatio( file.getAbsolutePath() );
      }

      /**
       * 计算宽高比
       *
       * @param filePath 图片文件路径
       *
       * @return 图片宽高比
       */
      public static float calculateAspectRatio ( String filePath ) {
            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile( filePath, options );
            return options.outWidth * 1f / options.outHeight;
      }

      /**
       * 计算宽高比
       *
       * @param fileDescriptor fileDescriptor
       *
       * @return 图片宽高比
       */
      public static float calculateAspectRatio ( FileDescriptor fileDescriptor ) {
            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );
            return options.outWidth * 1f / options.outHeight;
      }

      /**
       * 计算宽高比
       *
       * @param inputStream stream
       *
       * @return 图片宽高比
       */
      public static float calculateAspectRatio ( InputStream inputStream ) {
            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream( inputStream, null, options );
            return options.outWidth * 1f / options.outHeight;
      }

      /**
       * 计算图片采样率
       */
      private static int calculateInSampleSize (
          int bitmapWidth, int bitmapHeight, int reqWidth, int reqHeight ) {

            if( reqWidth == 0 || reqHeight == 0 ) {
                  return 1;
            }
            int inSampleSize = 1;

            //如果图片宽或者高 大于 view的宽高
            if( bitmapWidth > reqHeight || bitmapHeight > reqWidth ) {

                  //计算图片的宽高的一半
                  final int halfHeight = bitmapWidth / 2;
                  final int halfWidth = bitmapHeight / 2;

                  //如果原始图片宽高是 view宽高的 2,4,8,16 ... 倍以上,会压缩图片至宽高任意一条边略大于 view 的 宽高为止,图片质量不会太差
                  while( ( halfHeight / inSampleSize ) >= reqHeight
                      && ( halfWidth / inSampleSize ) >= reqWidth ) {
                        inSampleSize *= 2;
                  }
            }
            return inSampleSize;
      }

      /**
       * 计算图片极限缩放比例,使用宽高采样率中最大的
       */
      private static int calculateMaxInSampleSize (
          BitmapFactory.Options options, int reqWidth, int reqHeight ) {

            if( reqWidth == 0 || reqHeight == 0 ) {
                  return 1;
            }

            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            //如果图片宽或者高 大于 view的宽高
            if( height > reqHeight || width > reqWidth ) {

                  //分别计算宽高采样率,使用其中最大的值
                  while( ( height / inSampleSize ) >= reqHeight ) {
                        inSampleSize *= 2;
                  }

                  int heightSampleSize = inSampleSize;

                  while( ( width / inSampleSize ) >= reqWidth ) {
                        inSampleSize *= 2;
                  }

                  inSampleSize = heightSampleSize > inSampleSize ? heightSampleSize : inSampleSize;
            }
            return inSampleSize;
      }

      /**
       * 压缩图片至 宽度/高度 任一小于要求的 宽度/高度,使用采样率压缩
       *
       * @param context context
       * @param resId 资源id
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       *
       * @return 压缩后图片
       */
      public static Bitmap sampled (
          Context context,
          @DrawableRes int resId,
          int reqWidth,
          int reqHeight ) {

            return sampled( context, resId, reqWidth, reqHeight, Config.RGB_565 );
      }

      /**
       * 压缩图片至 宽度/高度 任一小于要求的 宽度/高度,使用采样率压缩
       *
       * @param context context
       * @param resId 资源id
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       * @param config 图片像素配置
       *
       * @return 压缩后图片
       */
      public static Bitmap sampled (
          Context context,
          @DrawableRes int resId,
          int reqWidth,
          int reqHeight,
          Config config ) {

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource( context.getResources(), resId, options );

            // 调用下面定义的方法计算inSampleSize值
            options.inSampleSize = calculateInSampleSize( options.outWidth, options.outHeight, reqWidth, reqHeight );
            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeResource( context.getResources(), resId, options );
      }

      /**
       * 压缩图片至 宽度/高度 任一小于要求的 宽度/高度,使用采样率压缩
       *
       * @param file 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       *
       * @return 压缩后图片
       */
      public static Bitmap sampled ( File file, int reqWidth, int reqHeight ) {

            return sampled( file, reqWidth, reqHeight, Config.RGB_565 );
      }

      /**
       * 压缩图片至 宽度/高度 任一小于要求的 宽度/高度,使用采样率压缩
       *
       * @param file 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       * @param config 图片像素配置
       *
       * @return 压缩后图片
       */
      public static Bitmap sampled (
          File file,
          int reqWidth,
          int reqHeight,
          Config config ) {

            return sampled( file.getAbsolutePath(), reqWidth, reqHeight, config );
      }

      /**
       * 压缩图片至 宽度/高度 任一小于要求的 宽度/高度,使用采样率压缩
       *
       * @param filePath 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       *
       * @return 压缩后图片
       */
      public static Bitmap sampled (
          String filePath,
          int reqWidth,
          int reqHeight ) {

            return sampled( filePath, reqWidth, reqHeight, Config.RGB_565 );
      }

      /**
       * 压缩图片至 宽度/高度 任一小于要求的 宽度/高度,使用采样率压缩
       *
       * @param filePath 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       * @param config 图片像素配置
       *
       * @return 压缩后图片
       */
      public static Bitmap sampled (
          String filePath,
          int reqWidth,
          int reqHeight,
          Config config ) {

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile( filePath, options );

            // 调用下面定义的方法计算inSampleSize值
            options.inSampleSize = calculateInSampleSize( options.outWidth, options.outHeight, reqWidth, reqHeight );

            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false;

            // 配置格式
            options.inPreferredConfig = config;

            return BitmapFactory.decodeFile( filePath, options );
      }

      /**
       * 压缩图片至 宽度/高度 任一小于要求的 宽度/高度,使用采样率压缩
       *
       * @param fileDescriptor 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       *
       * @return 压缩后图片
       */
      public static Bitmap sampled (
          FileDescriptor fileDescriptor, int reqWidth, int reqHeight ) {

            return sampled( fileDescriptor, reqWidth, reqHeight, Config.RGB_565 );
      }

      /**
       * 压缩图片至 宽度/高度 任一小于要求的 宽度/高度,使用采样率压缩
       *
       * @param fileDescriptor 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       * @param config 图片像素配置
       *
       * @return 压缩后图片
       */
      public static Bitmap sampled (
          FileDescriptor fileDescriptor,
          int reqWidth,
          int reqHeight,
          Config config ) {

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );

            // 调用下面定义的方法计算inSampleSize值
            options.inSampleSize = calculateInSampleSize( options.outWidth, options.outHeight, reqWidth, reqHeight );

            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false;

            // 配置格式
            options.inPreferredConfig = config;

            return BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );
      }

      /**
       * 压缩图片至 宽度/高度 任一小于要求的 宽度/高度,使用采样率压缩
       *
       * @param stream stream
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       *
       * @return 压缩后图片
       */
      public static Bitmap sampled ( InputStream stream, int reqWidth, int reqHeight ) {

            return sampled( stream, reqWidth, reqHeight, Config.RGB_565 );
      }

      /**
       * 压缩图片至 宽度/高度 任一小于要求的 宽度/高度,使用采样率压缩
       *
       * @param stream stream
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       * @param config 图片像素配置
       *
       * @return 压缩后图片
       */
      public static Bitmap sampled (
          InputStream stream,
          int reqWidth,
          int reqHeight,
          Config config ) {

            BufferedInputStream inputStream = getBufferedInputStream( stream );

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream( inputStream, null, options );

            // 调用下面定义的方法计算inSampleSize值
            options.inSampleSize = calculateInSampleSize( options.outWidth, options.outHeight, reqWidth, reqHeight );

            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false;
            // 配置格式
            options.inPreferredConfig = config;

            try {
                  inputStream.reset();
                  return BitmapFactory.decodeStream( inputStream, null, options );
            } catch(IOException e) {
                  e.printStackTrace();

                  try {
                        inputStream.close();
                  } catch(IOException e1) {
                        e1.printStackTrace();
                  }
            }

            return null;
      }

      /**
       * 压缩图片至 宽度/高度 全部小于要求的 宽度/高度,使用采样率压缩
       *
       * @param context context
       * @param resId 资源id
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       *
       * @return 压缩后图片
       */
      public static Bitmap sampledMost (
          Context context,
          @DrawableRes int resId,
          int reqWidth,
          int reqHeight ) {

            return sampledMost( context, resId, reqWidth, reqHeight, Config.RGB_565 );
      }

      /**
       * 压缩图片至 宽度/高度 全部小于要求的 宽度/高度,使用采样率压缩
       *
       * @param context context
       * @param resId 资源id
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       * @param config 图片像素配置
       *
       * @return 压缩后图片
       */
      public static Bitmap sampledMost (
          Context context,
          @DrawableRes int resId,
          int reqWidth,
          int reqHeight,
          Config config ) {

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource( context.getResources(), resId, options );

            // 调用下面定义的方法计算inSampleSize值
            options.inSampleSize = calculateMaxInSampleSize( options, reqWidth, reqHeight );
            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = config;

            return BitmapFactory.decodeResource( context.getResources(), resId, options );
      }

      /**
       * 压缩图片至 宽度/高度 全部小于要求的 宽度/高度,使用采样率压缩
       *
       * @param file 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       *
       * @return 压缩后图片
       */
      public static Bitmap sampledMost ( File file, int reqWidth, int reqHeight ) {

            return sampledMost( file, reqWidth, reqHeight, Config.RGB_565 );
      }

      /**
       * 压缩图片至 宽度/高度 全部小于要求的 宽度/高度,使用采样率压缩
       *
       * @param file 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       * @param config 图片像素配置
       *
       * @return 压缩后图片
       */
      public static Bitmap sampledMost (
          File file,
          int reqWidth,
          int reqHeight,
          Config config ) {

            return sampledMost( file.getAbsolutePath(), reqWidth, reqHeight, config );
      }

      /**
       * 压缩图片至 宽度/高度 全部小于要求的 宽度/高度,使用采样率压缩
       *
       * @param filePath 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       *
       * @return 压缩后图片
       */
      public static Bitmap sampledMost (
          String filePath,
          int reqWidth,
          int reqHeight ) {

            return sampledMost( filePath, reqWidth, reqHeight, Config.RGB_565 );
      }

      /**
       * 压缩图片至 宽度/高度 全部小于要求的 宽度/高度,使用采样率压缩
       *
       * @param filePath 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       * @param config 图片像素配置
       *
       * @return 压缩后图片
       */
      public static Bitmap sampledMost (
          String filePath,
          int reqWidth,
          int reqHeight,
          Config config ) {

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile( filePath, options );

            // 调用下面定义的方法计算inSampleSize值
            options.inSampleSize = calculateMaxInSampleSize( options, reqWidth, reqHeight );

            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false;

            // 配置格式
            options.inPreferredConfig = config;

            return BitmapFactory.decodeFile( filePath, options );
      }

      /**
       * 压缩图片至 宽度/高度 全部小于要求的 宽度/高度,使用采样率压缩
       *
       * @param fileDescriptor 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       *
       * @return 压缩后图片
       */
      public static Bitmap sampledMost (
          FileDescriptor fileDescriptor, int reqWidth, int reqHeight ) {

            return sampledMost( fileDescriptor, reqWidth, reqHeight, Config.RGB_565 );
      }

      /**
       * 压缩图片至 宽度/高度 全部小于要求的 宽度/高度,使用采样率压缩
       *
       * @param fileDescriptor 本地文件
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       * @param config 图片像素配置
       *
       * @return 压缩后图片
       */
      public static Bitmap sampledMost (
          FileDescriptor fileDescriptor,
          int reqWidth,
          int reqHeight,
          Config config ) {

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );

            // 调用下面定义的方法计算inSampleSize值
            options.inSampleSize = calculateMaxInSampleSize( options, reqWidth, reqHeight );

            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false;

            // 配置格式
            options.inPreferredConfig = config;

            return BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );
      }

      /**
       * 压缩图片至 宽度/高度 全部小于要求的 宽度/高度,使用采样率压缩
       *
       * @param stream stream
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       *
       * @return 压缩后图片
       */
      public static Bitmap sampledMost ( InputStream stream, int reqWidth, int reqHeight ) {

            return sampledMost( stream, reqWidth, reqHeight, Config.RGB_565 );
      }

      /**
       * 压缩图片至 宽度/高度 全部小于要求的 宽度/高度,使用采样率压缩
       *
       * @param stream stream
       * @param reqWidth 期望宽度
       * @param reqHeight 期望高度
       * @param config 图片像素配置
       *
       * @return 压缩后图片
       */
      public static Bitmap sampledMost (
          InputStream stream,
          int reqWidth,
          int reqHeight,
          Config config ) {

            BufferedInputStream inputStream = getBufferedInputStream( stream );

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream( inputStream, null, options );

            // 调用下面定义的方法计算inSampleSize值
            options.inSampleSize = calculateMaxInSampleSize( options, reqWidth, reqHeight );

            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false;
            // 配置格式
            options.inPreferredConfig = config;

            try {
                  inputStream.reset();
                  return BitmapFactory.decodeStream( inputStream, null, options );
            } catch(IOException e) {
                  e.printStackTrace();

                  try {
                        inputStream.close();
                  } catch(IOException e1) {
                        e1.printStackTrace();
                  }
            }

            return null;
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度全部满足设定的要求
       *
       * @param context 提供resource
       * @param id 资源id
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchSizeMost (
          Context context,
          @DrawableRes int id,
          int widthSize,
          int heightSize ) {

            return matchSizeMost( context, id, widthSize, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度全部满足设定的要求
       *
       * @param context 提供resource
       * @param id 资源id
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchSizeMost (
          Context context,
          @DrawableRes int id,
          int widthSize,
          int heightSize,
          Config config ) {

            /* 只读取尺寸 */

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource( context.getResources(), id, options );

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            float fateWidth = outWidth * 1f / widthSize;
            float fateHeight = outHeight * 1f / heightSize;

            /* 根据比率选择缩放边 */

            if( fateHeight > fateWidth ) {

                  options.inDensity = outHeight;
                  options.inTargetDensity = heightSize;
            } else {

                  options.inDensity = outWidth;
                  options.inTargetDensity = widthSize;
            }

            /* 完成解析 */

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            /* config */

            options.inPreferredConfig = config;

            return BitmapFactory.decodeResource( context.getResources(), id, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度全部满足设定的要求
       *
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchSizeMost (
          File file,
          int widthSize,
          int heightSize ) {

            return matchSizeMost( file, widthSize, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度全部满足设定的要求
       *
       * @param file bitmap file
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchSizeMost (
          File file,
          int widthSize,
          int heightSize,
          Config config ) {

            return matchSizeMost( file.getAbsolutePath(), widthSize, heightSize, config );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度全部满足设定的要求
       *
       * @param filePath bitmap filePath
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchSizeMost (
          String filePath,
          int widthSize,
          int heightSize ) {

            return matchSizeMost( filePath, widthSize, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度全部满足设定的要求
       *
       * @param filePath bitmap filePath
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchSizeMost (
          String filePath,
          int widthSize,
          int heightSize,
          Config config ) {

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile( filePath, options );

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            float fateWidth = outWidth * 1f / widthSize;
            float fateHeight = outHeight * 1f / heightSize;

            if( fateHeight > fateWidth ) {

                  options.inDensity = outHeight;
                  options.inTargetDensity = heightSize;
            } else {

                  options.inDensity = outWidth;
                  options.inTargetDensity = widthSize;
            }

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeFile( filePath, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度全部满足设定的要求
       *
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchSizeMost (
          FileDescriptor fileDescriptor,
          int widthSize,
          int heightSize ) {

            return matchSizeMost( fileDescriptor, widthSize, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度全部满足设定的要求
       *
       * @param fileDescriptor bitmap fileDescriptor
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchSizeMost (
          FileDescriptor fileDescriptor,
          int widthSize,
          int heightSize,
          Config config ) {

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            float fateWidth = outWidth * 1f / widthSize;
            float fateHeight = outHeight * 1f / heightSize;

            if( fateHeight > fateWidth ) {

                  options.inDensity = outHeight;
                  options.inTargetDensity = heightSize;
            } else {

                  options.inDensity = outWidth;
                  options.inTargetDensity = widthSize;
            }

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度全部满足设定的要求
       *
       * @param stream stream
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchSizeMost (
          InputStream stream,
          int widthSize,
          int heightSize ) {

            return matchSizeMost( stream, widthSize, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度全部满足设定的要求
       *
       * @param stream stream
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchSizeMost (
          InputStream stream,
          int widthSize,
          int heightSize,
          Config config ) {

            BufferedInputStream inputStream = getBufferedInputStream( stream );

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream( inputStream, null, options );

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            float fateWidth = outWidth * 1f / widthSize;
            float fateHeight = outHeight * 1f / heightSize;

            if( fateHeight > fateWidth ) {

                  options.inDensity = outHeight;
                  options.inTargetDensity = heightSize;
            } else {

                  options.inDensity = outWidth;
                  options.inTargetDensity = widthSize;
            }

            options.inScaled = true;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = config;

            try {
                  inputStream.reset();
                  return BitmapFactory.decodeStream( inputStream, null, options );
            } catch(IOException e) {
                  e.printStackTrace();

                  try {
                        inputStream.close();
                  } catch(IOException e1) {
                        e1.printStackTrace();
                  }
            }

            return null;
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度任一满足设定的要求
       *
       * @param context 提供resource
       * @param id 资源id
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchSize (
          Context context,
          @DrawableRes int id,
          int widthSize,
          int heightSize ) {

            return matchSize( context, id, widthSize, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度任一满足设定的要求
       *
       * @param context 提供resource
       * @param id 资源id
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchSize (
          Context context,
          @DrawableRes int id,
          int widthSize,
          int heightSize,
          Config config ) {

            /* 只读取尺寸 */

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource( context.getResources(), id, options );

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            float fateWidth = outWidth * 1f / widthSize;
            float fateHeight = outHeight * 1f / heightSize;

            /* 根据比率选择缩放边 */

            if( fateHeight < fateWidth ) {

                  options.inDensity = outHeight;
                  options.inTargetDensity = heightSize;
            } else {

                  options.inDensity = outWidth;
                  options.inTargetDensity = widthSize;
            }

            /* 完成解析 */

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            /* config */

            options.inPreferredConfig = config;

            return BitmapFactory.decodeResource( context.getResources(), id, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度任一满足设定的要求
       *
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchSize (
          File file,
          int widthSize,
          int heightSize ) {

            return matchSize( file, widthSize, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度任一满足设定的要求
       *
       * @param file bitmap file
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchSize (
          File file,
          int widthSize,
          int heightSize,
          Config config ) {

            return matchSize( file.getAbsolutePath(), widthSize, heightSize, config );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度任一满足设定的要求
       *
       * @param filePath bitmap filePath
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchSize (
          String filePath,
          int widthSize,
          int heightSize ) {

            return matchSize( filePath, widthSize, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度任一满足设定的要求
       *
       * @param filePath bitmap filePath
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchSize (
          String filePath,
          int widthSize,
          int heightSize,
          Config config ) {

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile( filePath, options );

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            float fateWidth = outWidth * 1f / widthSize;
            float fateHeight = outHeight * 1f / heightSize;

            if( fateHeight < fateWidth ) {

                  options.inDensity = outHeight;
                  options.inTargetDensity = heightSize;
            } else {

                  options.inDensity = outWidth;
                  options.inTargetDensity = widthSize;
            }

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeFile( filePath, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度任一满足设定的要求
       *
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchSize (
          FileDescriptor fileDescriptor,
          int widthSize,
          int heightSize ) {

            return matchSize( fileDescriptor, widthSize, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度任一满足设定的要求
       *
       * @param fileDescriptor bitmap fileDescriptor
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchSize (
          FileDescriptor fileDescriptor,
          int widthSize,
          int heightSize,
          Config config ) {

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            float fateWidth = outWidth * 1f / widthSize;
            float fateHeight = outHeight * 1f / heightSize;

            if( fateHeight < fateWidth ) {

                  options.inDensity = outHeight;
                  options.inTargetDensity = heightSize;
            } else {

                  options.inDensity = outWidth;
                  options.inTargetDensity = widthSize;
            }

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度任一满足设定的要求
       *
       * @param stream stream
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchSize (
          InputStream stream,
          int widthSize,
          int heightSize ) {

            return matchSize( stream, widthSize, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即:宽度或者高度任一满足设定的要求
       *
       * @param stream stream
       * @param widthSize 要求的宽度
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchSize (
          InputStream stream,
          int widthSize,
          int heightSize,
          Config config ) {

            BufferedInputStream inputStream = getBufferedInputStream( stream );

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream( inputStream, null, options );

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            float fateWidth = outWidth * 1f / widthSize;
            float fateHeight = outHeight * 1f / heightSize;

            if( fateHeight < fateWidth ) {

                  options.inDensity = outHeight;
                  options.inTargetDensity = heightSize;
            } else {

                  options.inDensity = outWidth;
                  options.inTargetDensity = widthSize;
            }

            options.inScaled = true;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = config;

            try {
                  inputStream.reset();
                  return BitmapFactory.decodeStream( inputStream, null, options );
            } catch(IOException e) {
                  e.printStackTrace();

                  try {
                        inputStream.close();
                  } catch(IOException e1) {
                        e1.printStackTrace();
                  }
            }

            return null;
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足宽度要求
       *
       * @param context 提供resource
       * @param id 资源id
       * @param widthSize 要求的宽度
       *
       * @return bitmap
       */
      public static Bitmap matchWidth (
          Context context,
          @DrawableRes int id,
          int widthSize ) {

            return matchWidth( context, id, widthSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足宽度要求
       *
       * @param context 提供resource
       * @param id 资源id
       * @param widthSize 要求的宽度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchWidth (
          Context context,
          @DrawableRes int id,
          int widthSize,
          Config config ) {

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource( context.getResources(), id, options );

            options.inDensity = options.outWidth;
            options.inTargetDensity = widthSize;

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeResource( context.getResources(), id, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足宽度要求
       *
       * @param widthSize 要求的宽度
       *
       * @return bitmap
       */
      public static Bitmap matchWidth (
          File file,
          int widthSize ) {

            return matchWidth( file, widthSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足宽度要求
       *
       * @param file bitmap file
       * @param widthSize 要求的宽度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchWidth (
          File file,
          int widthSize,
          Config config ) {

            return matchWidth( file.getAbsolutePath(), widthSize, config );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足宽度要求
       *
       * @param filePath bitmap filePath
       * @param widthSize 要求的宽度
       *
       * @return bitmap
       */
      public static Bitmap matchWidth (
          String filePath,
          int widthSize ) {

            return matchWidth( filePath, widthSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足宽度要求
       *
       * @param filePath bitmap filePath
       * @param widthSize 要求的宽度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchWidth (
          String filePath,
          int widthSize,
          Config config ) {

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile( filePath, options );

            options.inDensity = options.outWidth;
            options.inTargetDensity = widthSize;

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeFile( filePath, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足宽度要求
       *
       * @param widthSize 要求的宽度
       *
       * @return bitmap
       */
      public static Bitmap matchWidth (
          FileDescriptor fileDescriptor,
          int widthSize ) {

            return matchWidth( fileDescriptor, widthSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足宽度要求
       *
       * @param fileDescriptor bitmap fileDescriptor
       * @param widthSize 要求的宽度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchWidth (
          FileDescriptor fileDescriptor,
          int widthSize,
          Config config ) {

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );

            options.inDensity = options.outWidth;
            options.inTargetDensity = widthSize;

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足宽度要求
       *
       * @param stream stream
       * @param widthSize 要求的宽度
       *
       * @return bitmap
       */
      public static Bitmap matchWidth (
          InputStream stream,
          int widthSize ) {

            return matchWidth( stream, widthSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足宽度要求
       *
       * @param stream stream
       * @param widthSize 要求的宽度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchWidth (
          InputStream stream,
          int widthSize,
          Config config ) {

            BufferedInputStream inputStream = getBufferedInputStream( stream );

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream( inputStream, null, options );

            options.inDensity = options.outWidth;
            options.inTargetDensity = widthSize;
            options.inScaled = true;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = config;

            try {
                  inputStream.reset();
                  return BitmapFactory.decodeStream( inputStream, null, options );
            } catch(IOException e) {
                  e.printStackTrace();
                  try {
                        inputStream.close();
                  } catch(IOException e1) {
                        e1.printStackTrace();
                  }
            }

            return null;
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足高度要求
       *
       * @param context 提供resource
       * @param id 资源id
       * @param heightSize 要求的宽度
       *
       * @return bitmap
       */
      public static Bitmap matchHeight (
          Context context,
          @DrawableRes int id,
          int heightSize ) {

            return matchHeight( context, id, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足高度要求
       *
       * @param context 提供resource
       * @param id 资源id
       * @param heightSize 要求的宽度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchHeight (
          Context context,
          @DrawableRes int id,
          int heightSize,
          Config config ) {

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource( context.getResources(), id, options );

            options.inDensity = options.outHeight;
            options.inTargetDensity = heightSize;

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeResource( context.getResources(), id, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足高度要求
       *
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchHeight (
          File file,
          int heightSize ) {

            return matchHeight( file, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足高度要求
       *
       * @param file bitmap file
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchHeight (
          File file,
          int heightSize,
          Config config ) {

            return matchHeight( file.getAbsolutePath(), heightSize, config );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足高度要求
       *
       * @param filePath bitmap filePath
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchHeight (
          String filePath,
          int heightSize ) {

            return matchHeight( filePath, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足高度要求
       *
       * @param filePath bitmap filePath
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchHeight (
          String filePath,
          int heightSize,
          Config config ) {

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile( filePath, options );

            options.inDensity = options.outHeight;
            options.inTargetDensity = heightSize;

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeFile( filePath, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足高度要求
       *
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchHeight (
          FileDescriptor fileDescriptor,
          int heightSize ) {

            return matchHeight( fileDescriptor, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足高度要求
       *
       * @param fileDescriptor bitmap fileDescriptor
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchHeight (
          FileDescriptor fileDescriptor,
          int heightSize,
          Config config ) {

            BitmapFactory.Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );

            options.inDensity = options.outHeight;
            options.inTargetDensity = heightSize;

            options.inScaled = true;
            options.inJustDecodeBounds = false;

            options.inPreferredConfig = config;

            return BitmapFactory.decodeFileDescriptor( fileDescriptor, null, options );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足高度要求
       *
       * @param stream stream
       * @param heightSize 要求的高度
       *
       * @return bitmap
       */
      public static Bitmap matchHeight (
          InputStream stream,
          int heightSize ) {

            return matchHeight( stream, heightSize, Config.RGB_565 );
      }

      /**
       * 解析一个图片资源到匹配设定的尺寸,即满足高度要求
       *
       * @param stream stream
       * @param heightSize 要求的高度
       * @param config 图片像素配置
       *
       * @return bitmap
       */
      public static Bitmap matchHeight (
          InputStream stream,
          int heightSize,
          Config config ) {

            BufferedInputStream inputStream = getBufferedInputStream( stream );

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream( inputStream, null, options );

            options.inDensity = options.outHeight;
            options.inTargetDensity = heightSize;
            options.inScaled = true;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = config;

            try {
                  inputStream.reset();
                  return BitmapFactory.decodeStream( inputStream, null, options );
            } catch(IOException e) {
                  e.printStackTrace();

                  try {
                        inputStream.close();
                  } catch(IOException e1) {
                        e1.printStackTrace();
                  }
            }

            return null;
      }

      private static BufferedInputStream getBufferedInputStream ( InputStream stream ) {

            BufferedInputStream inputStream = new BufferedInputStream( stream, 128 );
            inputStream.mark( Integer.MAX_VALUE );
            return inputStream;
      }

      /**
       * 裁剪指定区域
       *
       * @param bitmap 原图
       * @param x 起始坐标x
       * @param y 起始坐标y
       * @param width 宽度
       * @param height 高度
       *
       * @return 裁剪后图片
       */
      public static Bitmap clip ( Bitmap bitmap, int x, int y, int width, int height ) {

            return Bitmap.createBitmap( bitmap, x, y, width, height );
      }

      /**
       * 高度方向从起始开始向下裁剪指定高度
       *
       * @param bitmap 原图
       * @param height 高度
       *
       * @return 裁剪后图片
       */
      public static Bitmap clipTop ( Bitmap bitmap, int height ) {

            return Bitmap.createBitmap( bitmap, 0, 0, bitmap.getWidth(), height );
      }

      /**
       * 高度方向从尾部开始向上裁剪指定高度
       *
       * @param bitmap 原图
       * @param height 高度
       *
       * @return 裁剪后图片
       */
      public static Bitmap clipBottom ( Bitmap bitmap, int height ) {

            return Bitmap.createBitmap(
                bitmap,
                0,
                bitmap.getHeight() - height,
                bitmap.getWidth(),
                height
            );
      }

      /**
       * 宽度方向从左开始向右裁剪指定宽度
       *
       * @param bitmap 原图
       * @param width 宽度
       *
       * @return 裁剪后图片
       */
      public static Bitmap clipLeft ( Bitmap bitmap, int width ) {

            return Bitmap.createBitmap( bitmap, 0, 0, width, bitmap.getHeight() );
      }

      /**
       * 宽度方向从右开始向左裁剪指定宽度
       *
       * @param bitmap 原图
       * @param width 宽度
       *
       * @return 裁剪后图片
       */
      public static Bitmap clipRight ( Bitmap bitmap, int width ) {

            return Bitmap
                .createBitmap( bitmap, bitmap.getWidth() - width, 0, width, bitmap.getHeight() );
      }

      /**
       * 从中心开始裁剪指定尺寸
       *
       * @param bitmap 原图
       * @param width 宽度
       * @param height 高度
       *
       * @return 裁剪后原图
       */
      public static Bitmap clipCenter ( Bitmap bitmap, int width, int height ) {

            int x = ( bitmap.getWidth() - width ) >> 1;
            int y = ( bitmap.getHeight() - height ) >> 1;

            return Bitmap
                .createBitmap( bitmap, x, y, width, height );
      }
}