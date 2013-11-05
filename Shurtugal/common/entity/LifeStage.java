/*
 ** 2012 August 23
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.common.entity;

/**
 * Modified From BarracudaATA's dragon mounts
 * 
 * @author Iamshortman
 */
public enum LifeStage
{

	HATCHLING(-72000, 0.3f, "HATCHLING"), JUVENILE(-48000, 0.6f, "JUVENILE"), ADULT(-24000, 1.0f, "ADULT"), ELDER(0, 1.2f, "ELDER");

	public static LifeStage getLifeStageForAge(int age)
	{
		if (age >= ELDER.ageLimit)
		{
			return ELDER;
		}
	    else if (age >= ADULT.ageLimit)
		{
			return ADULT;
		}
		else if (age >= JUVENILE.ageLimit)
		{
			return JUVENILE;
		}
		else
		{
			return HATCHLING;
		}
	}

	public final int ageLimit;
	private final float size;
	private final String name;
	
	private LifeStage(int ageLimit, float size, String str)
	{
		this.ageLimit = ageLimit;
		this.size = size;
		this.name = str;
	}

	/**
	 * @return the body size multiplicator
	 */
	public float getSize()
	{
		return size;
	}

	/**
	 * @return the age limit in ticks
	 */
	public int getAgeLimit()
	{
		return ageLimit;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}

}
