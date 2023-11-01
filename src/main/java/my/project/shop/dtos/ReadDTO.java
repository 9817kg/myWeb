package my.project.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadDTO {
	 	private Long id;

	    private String title;
	    private String author;
	    private String content;
	    private String date;
	    private String type;
}
