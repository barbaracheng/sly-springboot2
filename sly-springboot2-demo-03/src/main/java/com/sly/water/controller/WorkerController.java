package com.sly.water.controller;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.sly.water.entities.History;
import com.sly.water.entities.Worker;
import com.sly.water.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/23 16:06
 */
@Controller
@Slf4j
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    /**
     * 服务器上传图片路径
     */
    @Value("${location}")
    private String location;

    /**
     * 列出员工信息
     * @param model
     * @return
     */
    @RequestMapping("/workerList")
    public String listWorker(Model model) {
        List<Worker> list = workerService.listWorker();
        // 数据传入到前端
        model.addAttribute("workerList",list);
        // 跳转到客户列表页面
        return "workerList";
    }

    /**
     * 分页列出员工信息
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/workerListPage")
    public String listWorkerForPage(@RequestParam(value = "pageNum",defaultValue = "1")
                                                  Integer pageNum, Model model) {
        PageInfo<Worker> pageInfo = workerService.listWorkerForPage(pageNum);
        List<Worker> workerList = pageInfo.getList();
        //
        model.addAttribute("workerList",workerList);
        model.addAttribute("pageInfo",pageInfo);
        // 加一个属性pageData，表示是普通的分页查询，不是根据条件搜索
        model.addAttribute("pageData","listWorker");
        return "workerList";
    }


    /**
     * 搜索分页
     * 步骤：
     * 1 调  员工管理的搜索功能
     * 2 转
     *   将搜索的员工列表返回给前端(数据共享)
     *   跳转到员工列表页面
     * @param workerName
     * @param model
     * @return
     */
    @RequestMapping("/searchWorker")
    public String searchWorker(String workerName,
                               @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                               Model model) {
        if(log.isInfoEnabled()) {
            log.info("searchWorker name = "+ workerName);
        }
        PageInfo<Worker> pageInfo = workerService.searchWorker(pageNum,workerName);
        // 数据传入到前端
        model.addAttribute("workerList",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        // 按条件搜索分页
        model.addAttribute("pageData","searchData");
        model.addAttribute("data",workerName);

        // 跳转到员工列表页面
        return "workerList";
    }


    @RequestMapping("/preSaveWorker")
    public String preSaveWorker() {
        return "workerSave";
    }


    /**
     * 点击“提交”按钮，添加送水工
     * 步骤：
     * 1 获取浏览器上传的文件名称
     * 2 判断1是否为空，不为空的情况下才上传
     * 3 非空的情况
     *   3.1 获取上传文件的后缀。例如：上传文件MyPicture.png，获取后缀.png
     *   3.2 获取上传文件的前缀，使用系统当前时间的毫秒数作为前缀,例如：165645674675
     *   3.3 拼接前缀和后缀  165645674675.png
     *   3.4 获取YML配置文件的location，创建服务器文件上传的File对象
     *   3.5 创建上传目的路径对象
     *          location+拼接前缀和后缀
     *           E:/upLoad/picture/ 拼接 165645674675.png
     *       注意：如果E:/upLoad/picture/路径不存在需要我们手动创建
     *   3.6 上传文件
     *   3.7 将已上传的文件名称注入到worker对象，目的：保存送水工的时候，也会将工人照片路径写入数据库
     * 4 调用WorkerService业务逻辑层的saveWorker(Worker)方法将工人信息写入数据库
     * 5 添加成功返回送水工列表页面
     * @param worker 送水工信息
     * @param uploadFile 上传图片的信息
     * @return 添加成功返回送水工列表
     */
    @RequestMapping(value="/saveWorker",method = RequestMethod.POST)
    public String saveWorker(Worker worker, MultipartFile uploadFile) throws IOException {
        if (log.isInfoEnabled()) {
            log.info("save worker = "+worker);
            log.info(uploadFile.toString());
        }
        // 获取浏览器上传的文件
        String originalFilename = uploadFile.getOriginalFilename();
        if(log.isInfoEnabled()) {
            log.info("originalFilename:"+originalFilename);
        }
        // 条件成立：表示客户上传了照片
        if(StrUtil.isNotEmpty(originalFilename)){
            // 获取浏览器上传文件的后缀
            int lastIndex = originalFilename.lastIndexOf(".");
            String suffix = originalFilename.substring(lastIndex);
            // 获取浏览器上传文件的前缀
            long prefix = System.currentTimeMillis();
            // 上传新的文件名称
            String newUploadFile = prefix+suffix;
            // 创建上传服务器的路径
            File parentPath = new File(location);
            // 判断父路径是否存在，不存在创建
            if (!parentPath.exists()) {
                parentPath.mkdirs();
            }
            // 创建上传文件的最终的路径
            File finalFile = new File(parentPath, newUploadFile);
            // 上传文件
            uploadFile.transferTo(finalFile);
            // 上传文件注入到Worker对象
            worker.setWorkerImage(newUploadFile);
        }
        workerService.saveWorker(worker);
        return "redirect:/worker/workerListPage";
    }

    /**
     * 根据id删除员工
     * @param wid
     * @return
     */
    @RequestMapping(value = "/delWorker",method = RequestMethod.POST)
    @ResponseBody
    public String deleteWorker(@RequestParam("wid")Integer wid) {
        // 先查询送水历史中有没有该员工
        List<History> historyList = workerService.searchWorkerInHistoryById(wid);
        if(log.isInfoEnabled()) {
            log.info("delete wid = " + wid);
            log.info("historyList = " + historyList);
        }
        // 历史表中查询到了该员工的信息，返回删除失败
        if (historyList.size() > 0) {
            return "fail";
        }
        // 如果没有，则执行删除操作
        int rows = workerService.deleteWorkerById(wid);
        if(log.isInfoEnabled()) {
            log.info("delete wid = "+wid);
            log.info("historyList = "+historyList);
            log.info("delete worker rows = "+rows);
        }
        if (rows > 0){
            return "delete ok";
        }else {
            return "fail";
        }
    }


    /**
     * 点击修改跳转到这个方法
     * @param wid 员工id
     * @param model
     * @return 修改员工信息界面
     */
    @RequestMapping("/preUpdateWorker/{wid}")
    public String  preUpdateWorker(@PathVariable("wid") Integer wid,Model model) {
        if (log.isInfoEnabled()) {
            log.info("update worker wid ="+wid);
        }
        Worker worker = workerService.getWorkerById(wid);
        model.addAttribute("worker",worker);
        return "workerUpdate";
    }

    /**
     * 在修改员工信息页面点击“提交”时，跳转到此处
     * @param worker
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updateWorker",method = RequestMethod.POST)
    public String updateWorker(Worker worker,MultipartFile uploadFile) throws IOException {
        // 获取浏览器上传的文件
        String originalFilename = uploadFile.getOriginalFilename();
        if(log.isInfoEnabled()) {
            log.info("originalFilename:"+originalFilename);
            log.info("update worker = "+worker.toString());
        }
        // 条件成立：表示客户上传了照片
        if(StrUtil.isNotEmpty(originalFilename)) {
            // 获取浏览器上传文件的后缀
            int lastIndex = originalFilename.lastIndexOf(".");
            String suffix = originalFilename.substring(lastIndex);
            // 获取浏览器上传文件的前缀
            long prefix = System.currentTimeMillis();
            // 上传新的文件名称
            String newUploadFile = prefix+suffix;
            // 创建上传服务器的路径
            File parentPath = new File(location);
            // 判断父路径是否存在，不存在创建
            if (!parentPath.exists()) {
                parentPath.mkdirs();
            }
            // 创建上传文件的最终的路径
            File finalFile = new File(parentPath, newUploadFile);
            // 上传文件
            uploadFile.transferTo(finalFile);
            // 上传文件注入到Worker对象
            worker.setWorkerImage(newUploadFile);
        }
        int rows = workerService.updateWorker(worker);
        if(log.isInfoEnabled()) {
            log.info("update rows = "+rows);
        }
        return "redirect:/worker/workerListPage";
    }

    /**
     * 加工资、减工资
     * @param wid
     * @param workerSalary
     * @return
     */
    @RequestMapping(value = "/adjustSalary",method = RequestMethod.POST)
    @ResponseBody
    public String adjustSalary(@RequestParam("wid")Integer wid, @RequestParam("workerSalary")Integer workerSalary){
        if (log.isInfoEnabled()) {
            log.info("adjustSalary wid ="+wid+" workerSalary = "+workerSalary);
        }
        int rows = workerService.adjustSalary(wid, workerSalary);
        if (log.isInfoEnabled()) {
            log.info("adjustSalary rows = "+rows);
        }
        if (rows > 0) {
            return "success";
        } else{
            return "fail";
        }
    }

    /**
     * 统计未送水员工的信息
     * @return
     */
    @RequestMapping("/workerSendNoWater")
    public String workerSendNoWater(@RequestParam(value = "pageNum",defaultValue = "1")
                                                Integer pageNum,Model model) {
        PageInfo<Worker> pageInfo = workerService.searchworkerSendNoWater(pageNum);

        List<Worker> workerList = pageInfo.getList();
        //
        model.addAttribute("workerList",workerList);
        model.addAttribute("pageInfo",pageInfo);
        // 加一个属性pageData，表示是普通的分页查询，不是根据条件搜索
        model.addAttribute("pageData","SendNoWater");
        return "workerList";
    }
}
