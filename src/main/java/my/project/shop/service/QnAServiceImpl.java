package my.project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import my.project.shop.entity.QnA;
import my.project.shop.repository.QnARepository;

import java.util.List;

@Service
public class QnAServiceImpl implements QnAService {
    private final QnARepository qnaRepository;

    @Autowired
    public QnAServiceImpl(QnARepository qnaRepository) {
        this.qnaRepository = qnaRepository;
    }

    @Override
    public List<QnA> getAllQnA() {
        return qnaRepository.findAll();
    }

	@Override
	public Page<QnA> getQnAByPage(int page, int size) {
		 return qnaRepository.findAll(PageRequest.of(page - 1, size));
	}

    // QnA 관련 메서드 구현 가능
}
