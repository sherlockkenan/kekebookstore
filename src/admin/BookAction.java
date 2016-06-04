package admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import java.util.List;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;




import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.*;
import service.*;

public class BookAction extends ActionSupport implements ModelDriven<Book> {

	private static final long serialVersionUID = 1L;

	private Book book = new Book();

	private File upload;
	private String uploadFileName;//设置上传文件的文件名
    private String uploadContentType;//上传文件的类型
    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    public String getUploadContentType() {
        return uploadContentType;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String list() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pagenum = request.getParameter("pagenum");
		Service service = new Service();
		Page page = service.getBookPageData(pagenum);
		request.setAttribute("page", page);
		// request.getRequestDispatcher("/admin/book.jsp").forward(request,
		// response);
		return "list";
	}

	public String add() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			doupLoad(request);
			Service service = new Service();
			book.setId(UUID.randomUUID().toString());
			service.addBook(book);
			request.setAttribute("message", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败");
		}
		return "add";
		// request.getRequestDispatcher("/message.jsp").forward(request,
		// response);
	}

	private void doupLoad(HttpServletRequest request) {
		// 把上传的图片保存到images目录中，并把request中的请求参数封装到Book中

		try {

			
			String savefilename = makeFileName(uploadFileName);// 得到保存在硬盘的文件名
			String savepath = ServletActionContext.getServletContext().getRealPath("/images");
			FileInputStream in = new FileInputStream(upload);

			// create file
			String filepath = savepath + "\\" + savefilename;
			File file = new File(filepath);
			file.getParentFile().mkdirs();
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			int len = 0;
			byte buffer[] = new byte[1024];
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();

			book.setImage(savefilename);
			System.out.println(book.getImage());

			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String makeFileName(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		return UUID.randomUUID().toString() + "." + ext;
	}

	public String addUI() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Service service = new Service();
		List<Category> category = service.getAllCategory();
		request.setAttribute("categories", category);
		return "addUI";
		// request.getRequestDispatcher("/admin/addBook.jsp").forward(request,response);
	}

	public String delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String book_id = request.getParameter("book_id");
		Service service = new Service();
		service.deletebook(book_id);
		return "delete";
		// list(request, response);
	}

	@Override
	public Book getModel() {
		// TODO Auto-generated method stub
		return book;
	}

}
