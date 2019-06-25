package com.taotao.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.taotao.utils.FastDFSClient;

public class TestFastDFS {

	@Test
	public void uploadFile()throws Exception {
		//1.添加jar包
		//2.创建配置文件(配置tracker服务器地址)
		//3.加载配置文件
		ClientGlobal.init(
				"C:/Users/zp/Documents/GitHub/Study/项目练习/SSM分布式淘淘商城/taotao-manager-web/src/main/resources/resource/client.conf");
		//4.创建trackerClient 对象
		TrackerClient trackerClient=new TrackerClient();
		//5.使用TrackerClient对象获得trackerservice对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//6.创建一个StorageService 引用null就可
		StorageServer storageServer=null;
		//7.创建一个StorageClient对象.trackerservice . StorageService两个参数
		StorageClient storageClient=new StorageClient(trackerServer, storageServer);
		//8.使用StorageClient对象上传
		String [] s=storageClient.upload_appender_file("C:/Users/zp/Pictures/Camera Roll/bg.jpg", "jpg", null);
		for (String string : s) {
			System.out.println(string);
		}
	}
	
	
	//用工具类
	@Test
	public void uploadFile2()throws Exception {
		FastDFSClient fastDFSClient=new FastDFSClient("C:/Users/zp/Documents/GitHub/Study/项目练习/SSM分布式淘淘商城/taotao-manager-web/src/main/resources/resource/client.conf");
		String string=fastDFSClient.uploadFile("C:/Users/zp/Pictures/Camera Roll/bg.jpg");
		System.out.println(string);
	}
}
