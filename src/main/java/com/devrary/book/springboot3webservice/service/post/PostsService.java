package com.devrary.book.springboot3webservice.service.post;


import com.devrary.book.springboot3webservice.domain.posts.Posts;
import com.devrary.book.springboot3webservice.domain.posts.PostsRepository;
import com.devrary.book.springboot3webservice.web.dto.PostsResponseDTO;
import com.devrary.book.springboot3webservice.web.dto.PostsSaveRequestDTO;
import com.devrary.book.springboot3webservice.web.dto.PostsUpdateRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDTO requestDto){
        // 호출되는 메서드가 뭔지 패키지 클릭해서 확인해보기
        // postsRepository가 보라색이어야 인식된거임. 회색이면 다시 체크해보기.
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO requestDTO){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDTO.getTitle(), requestDTO.getContent());

        return id;
    }

    public PostsResponseDTO findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDTO(entity);
    }
}
