import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Class to monitor ranking strategy selection
 * @author Monty Dhanani
 *
 */
public class RankingListener implements ActionListener {
	
	public static final String GORANK = "goRank";
	
	private RankingGUI rg;
	private User u;
	
	
	
	/**
	 * Constructor
	 * @param rg the RankingGUI
	 * @param u the User in question
	 */
	public RankingListener(RankingGUI rg, User u) {
		
		//set object variables
		this.rg = rg;
		this.u = u;

		//choose which radio button to select based on User's strategy
		if (u.getRankingStrategy() instanceof PopularityStrategy) {
			rg.getStrategy1().setSelected(true);
		} else if (u.getRankingStrategy() instanceof StrategyTwo) {
			rg.getStrategy2().setSelected(true);
		} else if (u.getRankingStrategy() instanceof DistanceStrategy) {
			rg.getStrategy3().setSelected(true);
		} else {
			rg.getStrategy4().setSelected(true);
		}
		
		//if it is a producer must do the ssame for acting strategies
		if (u instanceof Producer) {
			rg.getStrategyA().setEnabled(true);
			rg.getStrategyB().setEnabled(true);
			if (((Producer) u).getActStrategy() == Producer.STRATEGY_A) {
				rg.getStrategyA().setSelected(true);
			}
			else {
				rg.getStrategyB().setSelected(true);
			}
		}

	}

	/**
	 * Respond to event
	 */
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource(); // Get the button pressed and
	
		
		String command = button.getActionCommand();
		
		//ranking strategy
		if (command.equals(GORANK)) { // Do something
			if (rg.getStrategy1().isSelected()) {
				u.setStrat(new PopularityStrategy());
			}
			else if (rg.getStrategy2().isSelected()) {
				u.setStrat(new StrategyTwo());
			}
			else if (rg.getStrategy3().isSelected()) {
				u.setStrat(new DistanceStrategy());
			}
			else {
				u.setStrat(new LikeFollowSimilarity());
			}
		}
		
		//acting strategy of a producer
		if (u instanceof Producer) {
			if (rg.getStrategyA().isSelected()) {
				((Producer) u).setActStrategy(Producer.STRATEGY_A);
			}
			else {
				((Producer) u).setActStrategy(Producer.STRATEGY_B);
			}
		}
		rg.getFrame().dispose();
	}
}
