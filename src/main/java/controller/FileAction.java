package controller;

import entity.FileSystem;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.UUID;

/**
 * @Title: FileAction
 * @Author Mr.罗
 * @Package controller
 * @Date 2023/11/21 18:20
 * @description: 处理文件上传的控制器
 */
@Controller
public class FileAction {

    /*
    request:多部件表单的请求对象
    return:上传文件的json对象

    文件上传的流程：
    1.先把文件保存到web服务器上
    2.再从web服务器上将文件上传到FastDFS上
     */

    @RequestMapping("upload")
//    MultipartHttpServletRequest是HttpServletRequest的强化版本，不仅可以装文本信息，还可以装图片文件信息
    public @ResponseBody FileSystem upload(MultipartHttpServletRequest request) throws Exception {
        FileSystem fileSystem = new FileSystem();

        //1.第一大步:先把文件保存到web服务器上
        //从请求页面中根据前端参数名获取到上传的文件对象
        MultipartFile file = request.getFile("fname");
        //从文件对象中获取文件的原始名称
        String oldFileName = file.getOriginalFilename();
        //通过字符串截取的方式，从原始文件名中获取文件的后缀  1.jpg
        String hou = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);//+1是因为从.出现的位置往后截取，不能截取为.jpg而是jpg
        //为了避免文件名相同而出现文件覆盖的情况生成全新的文件名
        String newFileName = UUID.randomUUID().toString() + "." + hou;//生成的前缀名+.+后缀名得到新文件的名
        //创建web服务器保存文件的目录(预先创建好D:\\FastDfsCase\\img\\upload目录，否则系统找不到路径，会抛出异常)
        File toSaveFile = new File("D:\\FastDfsCase\\img\\upload\\" + newFileName);//文件保存在本地的路径
        //将路径转换成文件
        file.transferTo(toSaveFile);
        //获取该文件在服务器中的绝对路径（也就是文件保存在本地的绝对路径）
        String newFilePath = toSaveFile.getAbsolutePath();


        //2:第二大步:把文件从web服务器上传到FastDFS
        ClientGlobal.initByProperties("config/fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        NameValuePair[] list = new NameValuePair[1];
        list[0] = new NameValuePair("fileName", oldFileName);
        String fileId = client.upload_file1(newFilePath, hou, list);
        trackerServer.close();

        //封装fileSystem数据对象
        fileSystem.setFileId(fileId);
        fileSystem.setFileName(oldFileName);
        fileSystem.setFilePath(fileId);//已经上传到FastDFS上，通过fileId来访问图片，所以fileId即为文件路径

        return fileSystem;
    }
}
