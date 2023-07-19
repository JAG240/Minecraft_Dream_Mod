package net.yippika.dreammod.event;

import ca.weblite.objc.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yippika.dreammod.DreamMod;
import net.minecraft.world.entity.player.Player;
import net.yippika.dreammod.items.ModItems;

import java.time.chrono.MinguoEra;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = DreamMod.MODID)
    public static class ForgeEvent{

        @SubscribeEvent
        public static void onLeftClick(PlayerInteractEvent.LeftClickBlock event)
        {
            Block hit = Minecraft.getInstance().level.getBlockState(event.getPos()).getBlock();

            if(hit == null)
                return;

            if(hit instanceof BedBlock && event.getEntity().getMainHandItem().getItem() == Items.STICK)
                event.getEntity().spawnAtLocation(new ItemStack(ModItems.dream_dust.get() , 1));

        }

        @SubscribeEvent
        public static void preventBedDestory(BlockEvent.BreakEvent event)
        {
            Player player = event.getPlayer();
            if(player.getMainHandItem().getItem() == Items.STICK && event.getState().getBlock() instanceof BedBlock)
            {
                event.setCanceled(true);
            }
        }

    }
}
