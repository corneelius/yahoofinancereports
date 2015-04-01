


public class YFArticleHandler implements ReportLoader.TagHandler{



	@Override
	public boolean acceptsTag(Tag tag) {
		if(tag.getName().equals("p"))
			return true;
		return false;
	}

	@Override
	public ReportLoader.Report getReport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleContent(String data, Tag[] innerTags, Tag[] enclosingTags) {
		System.out.println(Types.convertToPlainString(data));
		
	}

}
