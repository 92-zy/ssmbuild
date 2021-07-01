package com.zy.controller;

import com.zy.pojo.Books;
import com.zy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ssmbuild
 *  controller层调用service层
 * @author zy
 * @date 2021/6/30
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    //查询所有的书籍，并且返回一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddpage() {
        return "addBook";
    }
    //添加书籍的请求
    @RequestMapping("/addBook")
    public String addBook(Books book) {
        bookService.addBook(book);
        return "redirect:/book/allBook"; //重定向到所有的书籍界面
    }

    //跳转到书籍修改页面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(int id,Model model) {
        Books book = bookService.queryBookById(id);
        model.addAttribute("QueryBook", book);
        return "updateBook";
    }
    //修改书籍信息
    @RequestMapping("/updateBook")
    public String updateBook(Books book) {
        bookService.updateBook(book);
        return "redirect:/book/allBook";
    }

    //删除书籍信息 ===> 这里使用的是restful风格
    @RequestMapping("/deleteBook/{bookID}")
    public String deleteBook(@PathVariable("bookID") int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //根据用户输入的书名去查询
    @RequestMapping("/queryBookByName")
    public String queryBookByName(String queryBookName,Model model){
        List<Books> list= bookService.queryBookByName(queryBookName);
        //判断是否查询为空，为空则提示没有查询到
        if(list==null){
            list=bookService.queryAllBook();
            model.addAttribute("error","未查到");
        }

        model.addAttribute("list",list);
        return "allBook";
    }
}