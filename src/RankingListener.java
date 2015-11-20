import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class RankingListener implements ActionListener {
	private RankingGUI rg;
	private User u;
	public RankingListener(RankingGUI rg, User u) {
		this.rg = rg;
		this.u = u;

		if (u.getRankingStrategy() instanceof PopularityStrategy) {
			rg.getStrategy1().setSelected(true);
		} else if (u.getRankingStrategy() instanceof StrategyTwo) {
			rg.getStrategy2().setSelected(true);
		} else if (u.getRankingStrategy() instanceof DistanceStrategy) {
			rg.getStrategy3().setSelected(true);
		} else {
			rg.getStrategy4().setSelected(true);
		}
		
		
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

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource(); // Get the button pressed and
													// the action command
													// (button number).
		String command = button.getActionCommand();
		if (command.equals("goRank")) { // Do something
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
