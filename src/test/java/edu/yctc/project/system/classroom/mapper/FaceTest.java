package edu.yctc.project.system.classroom.mapper;

import edu.yctc.common.utils.file.FileUtils;
import edu.yctc.common.utils.file.FileUtilsAlter;
import edu.yctc.common.utils.http.HttpUtilsLuna;
import edu.yctc.face.function.FaceFunction;
import edu.yctc.face.function.impl.FaceFunctionImpl;
import edu.yctc.face.util.DealImage;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.infost.service.IInfostService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Luna@win10
 * @date 2020/4/20 18:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FaceTest {


    @Test
	public void bTest() throws Exception {

		String path = ResourceUtils.getURL("classpath:static/").getPath();
		String s=path+"tmp.jpg";

		String img ="http://iszychen.club:8087/iszychen/img/genesis/lesson/img/2.jpg";

	    File file=new File(path+"tmp.jpg");
	    if (file.exists()) {
		    FileUtils.deleteFile(path + "tmp.jpg");
	    }
		if (HttpUtilsLuna.isNetUrl(img)) {
			FileUtilsAlter.downloadHttpUrl(img, path, "tmp.jpg");
		}
//		FileUtilsAlter.downloadHttpUrl(img, path, "tmp.jpg");
	    if(file.exists()){
		    DealImage.paint(s);
	    }
	}

	@Test
	public void cTest() {
		boolean b = HttpUtilsLuna.isNetUrl("http://iszychen.club:8087/iszychen/img/genesis/lesson/img/2.jpg");
		System.out.println(b);
	}

    @Test
    public void aTest() {
        FaceFunction faceFunction = new FaceFunctionImpl();
        System.out.println(faceFunction);
    }
}
