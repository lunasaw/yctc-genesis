package edu.yctc.project.system.classroom.mapper;

import edu.yctc.face.OcrBaiduApi;
import edu.yctc.face.function.FaceFunction;
import edu.yctc.face.function.impl.FaceFunctionImpl;
import edu.yctc.project.system.knowledge.domain.Knowledge;
import edu.yctc.project.system.knowledge.mapper.KnowledgeMapper;
import edu.yctc.project.system.knowledge.service.IKnowledgeService;
import edu.yctc.project.system.knowledgePicture.domain.KnowledgePicture;
import edu.yctc.project.system.knowledgePicture.mapper.KnowledgePictureMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Luna@win10
 * @date 2020/4/21 21:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PictureTest {

	@Resource
	KnowledgePictureMapper knowledgePictureMapper;

	@Resource
	KnowledgeMapper knowledgeMapper;

	@Resource
	IKnowledgeService knowledge;

	@Test
	public void aTest() throws IOException {
		KnowledgePicture knowledgePicture=new KnowledgePicture();
		for (int i = 1; i <= 55; i++) {
			knowledgePicture.setId(Long.valueOf(i));
			String s="http://iszychen.club:8087/iszychen/img/genesis/lesson/583/img/"+i+".jpg";
			List<String> list = OcrBaiduApi.baiDuOcr(s);
			knowledgePicture.setPicture(s);
			knowledgePictureMapper.insertKnowledgePicture(knowledgePicture);
		}
	}

	@Test
	public void bTest() throws IOException {
		KnowledgePicture knowledgePicture = new KnowledgePicture();
		List<Knowledge> knowledges = knowledgeMapper.selectKnowledgeList(new Knowledge());
		List<KnowledgePicture> knowledgePictures = knowledgePictureMapper.selectKnowledgePictureList(knowledgePicture);
		for (int i = 0; i < knowledgePictures.size(); i++) {
			KnowledgePicture knowledgePicture1 = knowledgePictures.get(i);
			String picture = knowledgePicture1.getPicture();
			List<String> list = OcrBaiduApi.baiDuOcr(picture);
			for (int i1 = 0; i1 < knowledges.size(); i1++) {
				boolean knowledge = this.knowledge.checkKnowledge(knowledges.get(i1).getContent(), list.toString());
				if (knowledge){
					knowledgePicture1.setKnowledgeid(knowledges.get(i1).getId());
					knowledgePictureMapper.updateKnowledgePicture(knowledgePicture1);
				}
			}

		}
	}

}
