package com.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class ExcelController {

    @Autowired private UserMapper userMapper;

//      EasyExcel.read(fileName, DemoData .class, new DemoDataListener()).sheet()
//    // 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入默认1行
//        .headRowNumber(1).doRead();


    @PostMapping("readExcel")
    public void readExcel(@RequestParam("file") MultipartFile file) {
        ModelExcelListener<User> modelExcelListener = new ModelExcelListener<>();
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(file.getInputStream(), User.class, modelExcelListener).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).headRowNumber(1).build();
            excelReader.read(readSheet);
            //TODO 业务
            System.out.println(modelExcelListener.getDataList());
            System.err.println(modelExcelListener.getErrorList());
            for (User user : modelExcelListener.getDataList()) {
                userMapper.insert(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    @GetMapping("downloadFailedUsingJson")
    public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {

        List<User> users = userMapper.selectList(new QueryWrapper<User>());

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), User.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(users);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }


}
