package models.books;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","title","description","pagecount","excerpt","publishDate"})
public class BooksModel {

	@JsonProperty("id")
	private float id;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("pageCount")
	private float pageCount;
	
	@JsonProperty("excerpt")
	private String excerpt;
	
	@JsonProperty("publishDate")
	private String publishDate;

	// Getter Methods

	public float getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public float getPageCount() {
		return pageCount;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public String getPublishDate() {
		return publishDate;
	}

	// Setter Methods

	public void setId(float id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPageCount(float pageCount) {
		this.pageCount = pageCount;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String toString() {
		return "Book[id"+id+",title="+title +",description="+description +",pageCount="+pageCount+",excerpt="+ excerpt+",publishDate="+publishDate+"]";
	}
}
