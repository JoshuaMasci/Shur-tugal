package Shurtugal.common.sound;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundEvents 
{
	@ForgeSubscribe
	public void onSoundsLoaded(SoundLoadEvent event) 
	{
	// You add them the same way as you add blocks.
		System.out.println("--- LOADING SOUNDS NOW ---");
		event.manager.addSound("shurtugal:Green.ogg");
}
}