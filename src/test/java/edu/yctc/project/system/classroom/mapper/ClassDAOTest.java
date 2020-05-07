package edu.yctc.project.system.classroom.mapper;

import edu.yctc.face.function.FaceFunction;
import edu.yctc.face.function.impl.FaceFunctionImpl;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.infost.mapper.InfostMapper;
import edu.yctc.project.system.infost.service.IInfostService;
import edu.yctc.project.system.lesson.mapper.LessonMapper;
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.score.mapper.ClassScoreMapper;
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
 * @date 2020/4/17 19:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassDAOTest {

	@Resource
	InfostMapper infostMapper;

	@Resource
	ClassScoreMapper classScoreMapper;

	@Resource
	LessonMapper lessonMapper;

    // @Test
	public void aTest() {
		Infost infost=new Infost();
		infost.setType(0L);
		infost.setClassId(1L);
		List<Infost> infosts = infostMapper.selectInfostList(infost);
		System.out.println(infosts);
	}


	@Resource
	IInfostService infostService;

    // @Test
	public void bTest() throws IOException {

		FaceFunction faceFunction = new FaceFunctionImpl();
		faceFunction.checkByLessonId(583L, "http://iszychen.club:8087/iszychen/img/genesis/lesson/img/2.jpg");

	}

}
