package edu.yctc.project.system.info.controller;

import java.io.IOException;
import java.util.List;

import edu.yctc.framework.aspectj.lang.annotation.Log;
import edu.yctc.framework.aspectj.lang.enums.BusinessType;
import edu.yctc.framework.config.GenesisConfig;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.framework.web.page.TableDataInfo;
import edu.yctc.project.system.info.service.IFileInfoService;
import edu.yctc.common.constant.Constants;
import edu.yctc.common.utils.StringUtils;
import edu.yctc.common.utils.file.FileUploadUtils;
import edu.yctc.common.utils.file.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import edu.yctc.project.system.info.domain.FileInfo;
import edu.yctc.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件信息Controller
 * 
 * @author luna
 * @date 2020-03-28
 */
@Controller
@RequestMapping("/system/info")
public class FileInfoController extends BaseController {
    private String           prefix = "system/info";

    @Autowired
    private IFileInfoService fileInfoService;

    @RequiresPermissions("system:info:view")
    @GetMapping()
    public String info() {
        return prefix + "/info";
    }

    /**
     * 查询文件信息列表
     */
    @RequiresPermissions("system:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FileInfo fileInfo) {
        startPage();
        List<FileInfo> list = fileInfoService.selectFileInfoList(fileInfo);
        return getDataTable(list);
    }

    /**
     * 导出文件信息列表
     */
    @RequiresPermissions("system:info:export")
    @Log(title = "文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FileInfo fileInfo) {
        List<FileInfo> list = fileInfoService.selectFileInfoList(fileInfo);
        ExcelUtil<FileInfo> util = new ExcelUtil<FileInfo>(FileInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增文件信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存文件信息
     */
    @RequiresPermissions("system:info:add")
    @Log(title = "文件信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file, FileInfo fileInfo) throws IOException {
        // 上传文件路径
        String filePath = GenesisConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        fileInfo.setFilePath(fileName);
        return toAjax(fileInfoService.insertFileInfo(fileInfo));
    }

	/**
	 * 本地资源通用下载
	 */
	@GetMapping("/downloadFile/{fileId}")
	public void resourceDownload(@PathVariable("fileId") Long fileId, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		FileInfo fileInfo = fileInfoService.selectFileInfoById(fileId);
		// 本地资源路径
		String localPath = GenesisConfig.getProfile();
		// 数据库资源地址
		String downloadPath = localPath + StringUtils.substringAfter(fileInfo.getFilePath(), Constants.RESOURCE_PREFIX);
		// 下载名称
		String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition",
				"attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
		FileUtils.writeBytes(downloadPath, response.getOutputStream());
	}


    /**
     * 修改文件信息
     */
    @GetMapping("/edit/{fileId}")
    public String edit(@PathVariable("fileId") Long fileId, ModelMap mmap) {
        FileInfo fileInfo = fileInfoService.selectFileInfoById(fileId);
        mmap.put("fileInfo", fileInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存文件信息
     */
    @RequiresPermissions("system:info:edit")
    @Log(title = "文件信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FileInfo fileInfo) {
        return toAjax(fileInfoService.updateFileInfo(fileInfo));
    }

    /**
     * 删除文件信息
     */
    @RequiresPermissions("system:info:remove")
    @Log(title = "文件信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(fileInfoService.deleteFileInfoByIds(ids));
    }
}
