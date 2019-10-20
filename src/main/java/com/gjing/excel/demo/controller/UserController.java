package com.gjing.excel.demo.controller;

import cn.gjing.tools.excel.BigTitle;
import cn.gjing.tools.excel.ExcelFactory;
import com.gjing.excel.demo.entity.User;
import com.gjing.excel.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Gjing
 **/
@RestController
@Api(tags = "用户")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/user")
    @ApiOperation(value = "添加用户,用来导出")
    @ApiImplicitParam(name = "num", value = "添加数量", dataType = "int", required = true, paramType = "query")
    public ResponseEntity saveUser(int num) {
        userService.saveUser(num);
        return ResponseEntity.ok("添加成功");
    }

    @GetMapping("/user")
    @ApiOperation(value = "导出用户")
    public void exportUser(HttpServletResponse response) {
        List<User> users = userService.userList(0);
        ExcelFactory.createWriter(User.class, response)
                .bigTitle(() -> new BigTitle(3, "我是大标题"))
                .write(users)
                .flush();
    }

    @GetMapping("/user_empty")
    @ApiOperation(value = "导出用户模板")
    public void exportUserEmpty(HttpServletResponse response) {
        ExcelFactory.createWriter(User.class, response, "id", "createTime")
                .write(null)
                .flush();
    }

    @PostMapping("/user_import")
    @ApiOperation("导入")
    public ResponseEntity userImport(MultipartFile file) throws IOException {
        ExcelFactory.createReader(file.getInputStream(), User.class)
                .headerIndex(4)
                .read()
                .listener(e -> userService.saveUserList(e));
        return ResponseEntity.ok("导入成功");
    }
}
