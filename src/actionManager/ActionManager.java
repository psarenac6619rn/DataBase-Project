package actionManager;

import actionManager.ActionManager;

public class ActionManager {

	private static ActionManager instance;
	
	private AddAction addAction;
	private RemoveAction removeAction;
	private UpdateAction updateAction;
	private FilterAction filterAction;
	private SortAction sortAction;
	private CountAction countAction;
	private AverageAction averageAction;
	private SearchAction searchAction;
	private PocetnaTabelaAction pocetnaTabelaAction;
	
	
	private ActionManager() {
		init();
	}
	
	private void init() {
		
		addAction = new AddAction();
		removeAction = new RemoveAction();
		updateAction = new UpdateAction();
		filterAction = new FilterAction();
		sortAction = new SortAction();
		countAction = new CountAction();
		averageAction = new AverageAction();
		searchAction = new SearchAction();
		pocetnaTabelaAction = new PocetnaTabelaAction();
		
	}

	public static ActionManager getInstance() {
		if(instance == null) 
			instance = new ActionManager();
		return instance;
	}

	public AddAction getAddAction() {
		return addAction;
	}

	public RemoveAction getRemoveAction() {
		return removeAction;
	}

	public UpdateAction getUpdateAction() {
		return updateAction;
	}

	public void setAddAction(AddAction addAction) {
		this.addAction = addAction;
	}

	public void setRemoveAction(RemoveAction removeAction) {
		this.removeAction = removeAction;
	}

	public void setUpdateAction(UpdateAction updateAction) {
		this.updateAction = updateAction;
	}

	public FilterAction getFilterAction() {
		return filterAction;
	}

	public void setFilterAction(FilterAction filterAction) {
		this.filterAction = filterAction;
	}

	public SortAction getSortAction() {
		return sortAction;
	}

	public void setSortAction(SortAction sortAction) {
		this.sortAction = sortAction;
	}

	public CountAction getCountAction() {
		return countAction;
	}

	public void setCountAction(CountAction countAction) {
		this.countAction = countAction;
	}

	public AverageAction getAverageAction() {
		return averageAction;
	}

	public void setAverageAction(AverageAction averageAction) {
		this.averageAction = averageAction;
	}

	public SearchAction getSearchAction() {
		return searchAction;
	}

	public void setSearchAction(SearchAction searchAction) {
		this.searchAction = searchAction;
	}

	public PocetnaTabelaAction getPocetnaTabelaAction() {
		return pocetnaTabelaAction;
	}

	public void setPocetnaTabelaAction(PocetnaTabelaAction pocetnaTabelaAction) {
		this.pocetnaTabelaAction = pocetnaTabelaAction;
	}
	
}
