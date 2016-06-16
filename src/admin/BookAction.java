package admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import java.util.List;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import service.*;

public class BookAction extends ActionSupport implements ModelDriven<Book> {

	private static final long serialVersionUID = 1L;

	private Book book = new Book();
	private Category_service category_service;
	private File upload;
	private String uploadFileName;//设置上传文件的文件名
    private String uploadContentType;//上传文件的类型
    private Book_service book_service;
   

	public String list() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pagenum = request.getParameter("pagenum");
		
		Page page = book_service.getBookPageData(pagenum);
		request.setAttribute("page", page);
		// request.getRequestDispatcher("/admin/book.jsp").forward(request,
		// response);
		return "list";
	}

	public String add() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			doupLoad(request);
			book.setId(UUID.randomUUID().toString());
			book_service.addBook(book);
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

		List<Category> category = category_service.getAllCategory();
		request.setAttribute("categories", category);
		return "addUI";
		// request.getRequestDispatcher("/admin/addBook.jsp").forward(request,response);
	}

	public String delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String book_id = request.getParameter("book_id");
		book_service.deletebook(book_id);
		return "delete";
		// list(request, response);
	}
	public void get() throws Exception {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
		
			List<Book> book=book_service.getallbook();
			
			String jsonstr = JSONArray.fromObject(book).toString();
			
			response.getWriter().print(jsonstr);
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public  void update() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = request.getParameter("name");
		double price = Double.valueOf(request.getParameter("price"));
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String image = request.getParameter("image");
		String category_id = request.getParameter("category_id");
		String id = request.getParameter("id");
		int number = Integer.parseInt(request.getParameter("number"));
        Book book = new Book(id, name,author,price,image,description,category_id,number);

		try {
			book_service.updatebook(book);
			JSONObject jsonobj=new JSONObject();
			jsonobj=JSONObject.fromObject(book);
			response.getWriter().print(jsonobj.toString());
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("message", "update error");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}

	}
	public void delete1() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
	
		
		String book_id = request.getParameter("id");
		book_service.deletebook(book_id);
	
		JSONObject jsonobj=new JSONObject();
		jsonobj.put("success", true);
		response.getWriter().print(jsonobj.toString());

	}
	@Override
	public Book getModel() {
		// TODO Auto-generated method stub
		return book;
	}
	 
    public Book_service getBook_service() {
		return book_service;
	}
	public void setBook_service(Book_service book_service) {
		this.book_service = book_service;
	}
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

	public Category_service getCategory_service() {
		return category_service;
	}

	public void setCategory_service(Category_service category_service) {
		this.category_service = category_service;
	}
	
}
