package com.wu1g.framework.web.servlet;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wu1g.framework.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Servlet implementation class upload
 */
@WebServlet("/fileUpload")
@Slf4j
public class UploadServlet extends HttpServlet {
	private static final long				serialVersionUID	= 1429479051595590315L;
	// 线程池 默认大小
	public static final ExecutorService		threadPool			= Executors.newScheduledThreadPool( Integer.parseInt( ResourcesUtil.getData( "IMAGE_EXECUTOR_SERVICE_SIZE" ).trim() ) );
	// n0图象宽度
	private static final int				IMAGE_N0_WIDTH		= Integer.parseInt( ResourcesUtil.getData( "IMAGE_N0_WIDTH" ).trim() );
	// n0图象高度
	private static final int				IMAGE_N0_HEIGHT		= Integer.parseInt( ResourcesUtil.getData( "IMAGE_N0_HEIGHT" ).trim() );
	// n1图象宽度
	private static final int				IMAGE_N1_WIDTH		= Integer.parseInt( ResourcesUtil.getData( "IMAGE_N1_WIDTH" ).trim() );
	// n1图象高度
	private static final int				IMAGE_N1_HEIGHT		= Integer.parseInt( ResourcesUtil.getData( "IMAGE_N1_HEIGHT" ).trim() );
	// n2图象宽度
	private static final int				IMAGE_N2_WIDTH		= Integer.parseInt( ResourcesUtil.getData( "IMAGE_N2_WIDTH" ).trim() );
	// n2图象高度
	private static final int				IMAGE_N2_HEIGHT		= Integer.parseInt( ResourcesUtil.getData( "IMAGE_N2_HEIGHT" ).trim() );
	// n3图像宽度
	private static final int				IMAGE_N3_WIDTH		= Integer.parseInt( ResourcesUtil.getData( "IMAGE_N3_WIDTH" ).trim() );
	// n3图像高度
	private static final int				IMAGE_N3_HEIGHT		= Integer.parseInt( ResourcesUtil.getData( "IMAGE_N3_HEIGHT" ).trim() );
	//
	private static final SimpleDateFormat	sdf					= new SimpleDateFormat( "/yyyyMM/" );

	/**
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info( "UploadAction init" );
 
		response.reset();
		response.setContentType( "text/plain;charset=" + PathCommonConstant.DEFAULT_ENCODE );

		//--项目id
		String projId=request.getParameter( "projId" );
		
		// 上传文件类型（图片image，动画flash，多媒体media，文件file）
		String dirName = request.getParameter( "dir" );
		if (ValidatorUtil.isNullEmpty( dirName )) {
			dirName = "file";
		}
		// 是否选择了文件
		if (!ServletFileUpload.isMultipartContent( request )) {
			response.getWriter().write( getError( "请选择文件" ) );
			return;
		}
		// 路径变量
		String savePath = ""; // 文件保存路径
		String saveUrl = ""; // 文件保存目录URL(返前台)
		// n0到n4保存路径
		String savePathN0 = "";
		String savePathN1 = "";
		String savePathN2 = "";
		String savePathN3 = "";
		String savePathN4 = "";
		// 子文件夹
		String forDate = sdf.format( new Date() );
		if(ValidatorUtil.notEmpty( projId )){
			forDate="/"+projId+"/";
		}
		// 根据是否是图片处理保存路径
		if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals( dirName )) {
			// 文件保存目录路径
			savePathN0 = ResourcesUtil.getData( "UPLOAD_ROOT_FOLDER" ) + dirName + "/" + "n0" + forDate;
			savePathN1 = ResourcesUtil.getData( "UPLOAD_ROOT_FOLDER" ) + dirName + "/" + "n1" + forDate;
			savePathN2 = ResourcesUtil.getData( "UPLOAD_ROOT_FOLDER" ) + dirName + "/" + "n2" + forDate;
			savePathN3 = ResourcesUtil.getData( "UPLOAD_ROOT_FOLDER" ) + dirName + "/" + "n3" + forDate;
			savePathN4 = ResourcesUtil.getData( "UPLOAD_ROOT_FOLDER" ) + dirName + "/" + "n4" + forDate;
			// 创建文件夹
			File saveDirFileN0 = new File( savePathN0 );
			if (!saveDirFileN0.exists()) {
				saveDirFileN0.mkdirs();
			}
			File saveDirFileN1 = new File( savePathN1 );
			if (!saveDirFileN1.exists()) {
				saveDirFileN1.mkdirs();
			}
			File saveDirFileN2 = new File( savePathN2 );
			if (!saveDirFileN2.exists()) {
				saveDirFileN2.mkdirs();
			}
			File saveDirFileN3 = new File( savePathN3 );
			if (!saveDirFileN3.exists()) {
				saveDirFileN3.mkdirs();
			}
			// 检查写权限
			if (!saveDirFileN0.canWrite()) {
				response.getWriter().write( getError( "上传目录没有写权限" ) );
				return;
			}
			if (!saveDirFileN1.canWrite()) {
				response.getWriter().write( getError( "上传目录没有写权限" ) );
				return;
			}
			if (!saveDirFileN2.canWrite()) {
				response.getWriter().write( getError( "上传目录没有写权限" ) );
				return;
			}
			if (!saveDirFileN3.canWrite()) {
				response.getWriter().write( getError( "上传目录没有写权限" ) );
				return;
			}
			// 原图将在n4保存
			savePath = savePathN4;
			// 文件保存目录URL
			String isRichEditor = request.getParameter( PathCommonConstant.IS_RICH_EDITOR );
			if (!ValidatorUtil.isNullEmpty( isRichEditor ) && isRichEditor.equals( "1" )) {
				// 在富文本编辑器内上传的返n3
				saveUrl = ResourcesUtil.getData( "UPLOAD_ROOT_FOLDER_URL" ) + "/" + dirName + "/" + "n3" + forDate;
			} else {
				// 在其他地方上传的返n1
				saveUrl = ResourcesUtil.getData( "UPLOAD_ROOT_FOLDER_URL" ) + "/" + dirName + "/" + "n1" + forDate;
			}
		} else {
			// 非图片的保存路径中没有Nx
			// 文件保存目录路径
			savePath = ResourcesUtil.getData( "UPLOAD_ROOT_FOLDER" ) + dirName + forDate;

			// 文件保存目录URL
			saveUrl = ResourcesUtil.getData( "UPLOAD_ROOT_FOLDER_URL" ) + "/" + dirName + forDate;
		}

		// 创建文件夹
		File saveDirFile = new File( savePath );
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		// 检查目录写权限
		if (!saveDirFile.canWrite()) {
			response.getWriter().write( getError( "上传目录没有写权限" ) );
			return;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload( factory );
		upload.setHeaderEncoding( "UTF-8" );
		List<FileItem> items = null;
		try {
			items = upload.parseRequest( request );
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		Iterator<?> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			// String fileName = item.getName();
			// long fileSize = item.getSize();
			// 普通文件表单，没有FORM
			if (!item.isFormField()) {

				// 上传文件的名称
				String name = item.getName();
				if (ValidatorUtil.isNullEmpty( name )) {
					response.getWriter().write( getError( "上传文件没有文件名" ) );
					return;
				}

				// 检查文件大小
				if (!checkFileSize( item.getSize(), dirName )) {
					response.getWriter().write( getError( "上传文件大小超过限制" ) );
					return;
				}

				// 检查扩展名
				String fileExt = WebUtil.getFileExt( name );
				if (!checkExt( dirName, fileExt )) {
					response.getWriter().write( getError( "上传文件扩展名不正确" ) );
					return;
				}

				// 创建新的文件名
				String newFileName = WebUtil.getTime( "yyyyMMddHHmmss" ) + WebUtil.getRandomString( 5 ) + "." + fileExt;

				try {
					File uploadedFile = new File( savePath, newFileName );
					item.write( uploadedFile );

					// // 图加水印
					// if ("image".equals(dirName) && "product".equals(key)
					// && "show".equals(picType)) {
					// ImageUtil.watermark(savePath, newFileName);
					// }

					// // 生成缩略图
					// createThumbnails(dirName, key, savePath, newFileName,
					// picType);

					// 对于图片,存储n0,n1,n2,n3
					if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals( dirName )) {
						ImageUtil.resizeNx( savePath, savePathN1, newFileName, newFileName, IMAGE_N1_WIDTH, IMAGE_N1_HEIGHT, false );
						if(!ValidatorUtil.notEmpty( projId )){
							ImageUtil.resizeNx( savePath, savePathN0, newFileName, newFileName, IMAGE_N0_WIDTH, IMAGE_N0_HEIGHT, true );
							ImageUtil.resizeNx( savePath, savePathN2, newFileName, newFileName, IMAGE_N2_WIDTH, IMAGE_N2_HEIGHT, false );
							ImageUtil.resizeNx( savePath, savePathN3, newFileName, newFileName, IMAGE_N3_WIDTH, IMAGE_N3_HEIGHT, false );

						}
					}

				} catch (Exception e) {
					response.getWriter().write( getError( "上传文件失败" ) );
					log.error( PathCommonConstant.LOG_ERROR_TITLE, e );
					return;
				}

				Map<String,Object> obj = new HashMap<String,Object>();
				obj.put( "error", 0 );
				obj.put( "fileUrl", (saveUrl + newFileName) );
				obj.put( "fileName", name );
				response.getWriter().write( JSON.toJSONString( obj ) );
			}
		}
	}

	/**
	 * 检查文件大小
	 * 
	 * @param size
	 *            上传文件的大小
	 * @param type
	 *            上传文件的类型
	 * @return
	 */
	private boolean checkFileSize(long size, String type) {
		// TODO Auto-generated method stub
		boolean flag = true;
		if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals( type )) {
			if (size > PathCommonConstant.UPLOAD_PIC_MAX_SIZE) {
				flag = false;
			}
		} else if (PathCommonConstant.UPLOAD_CATAGORY_FLASH.equals( type ) || PathCommonConstant.UPLOAD_CATAGORY_MEDIA.equals( type )) {
			if (size > PathCommonConstant.UPLOAD_VIDEO_MAX_SIZE) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * <p>
	 * 返回JSON消息
	 * </p>
	 * 
	 * @return JSON字符串
	 * @throws JsonProcessingException 
	 */
	private String getError(String message) {
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put( "error", 1 );
		obj.put( "message", message );
		return JSON.toJSONString( obj );
	}

	/**
	 * <p>
	 * 检查文件扩展名是否合法
	 * </p>
	 * 
	 * @param type
	 *            上传类型
	 * @param ext
	 *            文件扩展名
	 * @return true:合法 false:不合法
	 */
	private boolean checkExt(String type, String ext) {
		if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals( type )) {
			// 图片
			return PathCommonConstant.UPLOAD_TYPE_IMAGE.contains( ext );
		} else if (PathCommonConstant.UPLOAD_CATAGORY_FLASH.equals( type ) || PathCommonConstant.UPLOAD_CATAGORY_MEDIA.equals( type )) {
			// 动画或者多媒体
			return PathCommonConstant.UPLOAD_TYPE_MEDIA.contains( ext );
		} else if (PathCommonConstant.UPLOAD_CATAGORY_FILE.equals( type )) {
			// 普通文件
			return true;
		}
		return false;
	}
}
