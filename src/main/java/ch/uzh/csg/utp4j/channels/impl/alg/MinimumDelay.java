package ch.uzh.csg.utp4j.channels.impl.alg;

public class MinimumDelay {

	private long ourTimeStamp = 0;
	private long minDelay = 0;
	
	private long theirTimeStamp = 0;
	private long theirMinDelay = 0;
	
	private static long MINIMUM_DIFFERENCE_TIMESTAMP_MICROSEC = 120000000L;
	
	public long getCorrectedMinDelay() {
		return minDelay;
	}

	public void updateOurDelay(long difference, long timestamp) {

		if ((ourTimeStamp - this.ourTimeStamp >= MINIMUM_DIFFERENCE_TIMESTAMP_MICROSEC) || (this.ourTimeStamp == 0 && this.minDelay == 0)) {
			this.ourTimeStamp = timestamp;
			this.minDelay = difference;
		} else {
			if (difference < this.minDelay) {
				this.ourTimeStamp = timestamp;
				this.minDelay = difference;
			}
		}
		
	}

	public void updateTheirDelay(long theirDifference, long timeStampNow) {
		if ((timeStampNow - this.theirTimeStamp >= MINIMUM_DIFFERENCE_TIMESTAMP_MICROSEC) || (this.theirTimeStamp == 0 && this.theirMinDelay == 0)) {
			theirMinDelay = theirDifference;
			this.theirTimeStamp = timeStampNow;
		} else {
			if (theirDifference < theirMinDelay) {
				theirTimeStamp = timeStampNow;
				theirMinDelay = theirDifference;
				minDelay += (theirMinDelay - theirDifference);
			}
		}
	}

	public long getTheirMinDelay() {
		return theirMinDelay;		
	}

}