package com.arcanumLudum.ALCore.world;

import java.util.Random;
import java.util.concurrent.Callable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityDropParticleFX;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.client.particle.EntityFishWakeFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityFootStepFX;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntityHugeExplodeFX;
import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntityNoteFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySnowShovelFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.client.particle.EntitySuspendFX;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.world.biome.BiomeGenBase;

import com.arcanumLudum.ALCore.ALCore;
import com.arcanumLudum.ALCore.colour.ColourRGB;
import com.arcanumLudum.ALCore.math.vec.Vector3;
import com.arcanumLudum.ALCore.particles.EntityColoredFX;
import com.arcanumLudum.ALCore.world.gen.ShapeHelper;

public class WorldHelper 
{
	@Deprecated
	public static void toBiome(int r, BiomeGenBase biome)
	{
		Random rand = new Random();
		
		MovingObjectPosition position = ALCore.instance.mc.objectMouseOver;
		Block block = ALCore.instance.world().getBlock(position.blockX, position.blockY, position.blockZ);

	    if (block.getMaterial() != Material.air)
	    {
	    	int blockX = (int)ALCore.instance.player.lastTickPosX - (int)(ALCore.instance.player.posX - ALCore.instance.player.lastTickPosX);
	        int blockY = (int)ALCore.instance.player.lastTickPosY - (int)(ALCore.instance.player.posY - ALCore.instance.player.lastTickPosY);
	        int blockZ = (int)ALCore.instance.player.lastTickPosZ - (int)(ALCore.instance.player.posZ - ALCore.instance.player.lastTickPosZ);
	        
	        ShapeHelper.replaceBiomeSphereCentered(new Vector3(blockX, blockY, blockZ), biome, 5, 5, 5, true, true);
	    }
	}
	
	public static boolean setBlock(Vector3 pos, Block block)
	{
		return ALCore.instance.world.setBlock((int)pos.x, (int)pos.y, (int)pos.z, block);
	}
	
	public static boolean replaceBlock(Vector3 pos, Block block)
	{
		if(ALCore.instance.world().getBlock((int)pos.x, (int)pos.y, (int)pos.z) != Blocks.air)
		{
			return ALCore.instance.world().setBlock((int)pos.x, (int)pos.y, (int)pos.z, block);
		}
		else
		{
			return false;
		}
	}
	
	public static boolean spawnParticle(String particleName, ColourRGB colour, Vector3 pos, float velX, float velY, float velZ)
	{
		//spawnParticle(particleName, colour, x, y, z, velX, velY, velZ);
		
		spawnParticle(particleName, colour, pos.x, pos.y, pos.z, velX, velY, velZ);
		
		return true;
	}
	
    /**
     * Spawns a particle. Arg: particleType, x, y, z, velX, velY, velZ
     */
	private static void spawnParticle(String p_72708_1_, ColourRGB colour, final double p_72708_2_, final double p_72708_4_, final double p_72708_6_, double p_72708_8_, double p_72708_10_, double p_72708_12_)
    {
        try
        {
            doSpawnParticle(p_72708_1_, colour, p_72708_2_, p_72708_4_, p_72708_6_, p_72708_8_, p_72708_10_, p_72708_12_);
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Exception while adding particle");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Particle being added");
            crashreportcategory.addCrashSection("Name", p_72708_1_);
            crashreportcategory.addCrashSectionCallable("Position", new Callable()
            {
                private static final String __OBFID = "CL_00000955";
                public String call()
                {
                    return CrashReportCategory.func_85074_a(p_72708_2_, p_72708_4_, p_72708_6_);
                }
            });
            throw new ReportedException(crashreport);
        }
    }
    
    /**
     * Spawns a particle. Arg: particleType, x, y, z, velX, velY, velZ
     */
    private static EntityFX doSpawnParticle(String p_72726_1_, ColourRGB colour, double p_72726_2_, double p_72726_4_, double p_72726_6_, double p_72726_8_, double p_72726_10_, double p_72726_12_)
    {
        if (ALCore.instance.mc != null && ALCore.instance.mc.renderViewEntity != null && ALCore.instance.mc.effectRenderer != null)
        {
            int i = ALCore.instance.mc.gameSettings.particleSetting;

            if (i == 1 && ALCore.instance.world().rand.nextInt(3) == 0)
            {
                i = 2;
            }

            double d6 = ALCore.instance.mc.renderViewEntity.posX - p_72726_2_;
            double d7 = ALCore.instance.mc.renderViewEntity.posY - p_72726_4_;
            double d8 = ALCore.instance.mc.renderViewEntity.posZ - p_72726_6_;
            EntityFX entityfx = null;

            if (p_72726_1_.equals("hugeexplosion"))
            {
                ALCore.instance.mc.effectRenderer.addEffect(entityfx = new EntityHugeExplodeFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_));
            }
            else if (p_72726_1_.equals("largeexplode"))
            {
                ALCore.instance.mc.effectRenderer.addEffect(entityfx = new EntityLargeExplodeFX(ALCore.instance.mc.renderEngine, ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_));
            }
            else if (p_72726_1_.equals("fireworksSpark"))
            {
                ALCore.instance.mc.effectRenderer.addEffect(entityfx = new EntityFireworkSparkFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, ALCore.instance.mc.effectRenderer));
            }

            if (entityfx != null)
            {
                return (EntityFX)entityfx;
            }
            else
            {
                double d9 = 16.0D;

                if (d6 * d6 + d7 * d7 + d8 * d8 > d9 * d9)
                {
                    return null;
                }
                else if (i > 1)
                {
                    return null;
                }
                else
                {
                    if (p_72726_1_.equals("colourCrit"))
                    {
                        entityfx = new EntityColoredFX(ALCore.instance.world(), colour, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("bubble"))
                    {
                        entityfx = new EntityBubbleFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("suspended"))
                    {
                        entityfx = new EntitySuspendFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("depthsuspend"))
                    {
                        entityfx = new EntityAuraFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("townaura"))
                    {
                        entityfx = new EntityAuraFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("crit"))
                    {
                        entityfx = new EntityCritFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("magicCrit"))
                    {
                        entityfx = new EntityCritFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                        ((EntityFX)entityfx).setRBGColorF(((EntityFX)entityfx).getRedColorF() * 0.3F, ((EntityFX)entityfx).getGreenColorF() * 0.8F, ((EntityFX)entityfx).getBlueColorF());
                        ((EntityFX)entityfx).nextTextureIndexX();
                    }
                    else if (p_72726_1_.equals("smoke"))
                    {
                        entityfx = new EntitySmokeFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("mobSpell"))
                    {
                        entityfx = new EntitySpellParticleFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, 0.0D, 0.0D, 0.0D);
                        ((EntityFX)entityfx).setRBGColorF((float)p_72726_8_, (float)p_72726_10_, (float)p_72726_12_);
                    }
                    else if (p_72726_1_.equals("mobSpellAmbient"))
                    {
                        entityfx = new EntitySpellParticleFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, 0.0D, 0.0D, 0.0D);
                        ((EntityFX)entityfx).setAlphaF(0.15F);
                        ((EntityFX)entityfx).setRBGColorF((float)p_72726_8_, (float)p_72726_10_, (float)p_72726_12_);
                    }
                    else if (p_72726_1_.equals("spell"))
                    {
                        entityfx = new EntitySpellParticleFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("instantSpell"))
                    {
                        entityfx = new EntitySpellParticleFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                        ((EntitySpellParticleFX)entityfx).setBaseSpellTextureIndex(144);
                    }
                    else if (p_72726_1_.equals("witchMagic"))
                    {
                        entityfx = new EntitySpellParticleFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                        ((EntitySpellParticleFX)entityfx).setBaseSpellTextureIndex(144);
                        float f = ALCore.instance.world().rand.nextFloat() * 0.5F + 0.35F;
                        ((EntityFX)entityfx).setRBGColorF(1.0F * f, 0.0F * f, 1.0F * f);
                    }
                    else if (p_72726_1_.equals("note"))
                    {
                        entityfx = new EntityNoteFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("portal"))
                    {
                        entityfx = new EntityPortalFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("enchantmenttable"))
                    {
                        entityfx = new EntityEnchantmentTableParticleFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("explode"))
                    {
                        entityfx = new EntityExplodeFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("flame"))
                    {
                        entityfx = new EntityFlameFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("lava"))
                    {
                        entityfx = new EntityLavaFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_);
                    }
                    else if (p_72726_1_.equals("footstep"))
                    {
                        entityfx = new EntityFootStepFX(ALCore.instance.mc.renderEngine, ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_);
                    }
                    else if (p_72726_1_.equals("splash"))
                    {
                        entityfx = new EntitySplashFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("wake"))
                    {
                        entityfx = new EntityFishWakeFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("largesmoke"))
                    {
                        entityfx = new EntitySmokeFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, 2.5F);
                    }
                    else if (p_72726_1_.equals("cloud"))
                    {
                        entityfx = new EntityCloudFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("reddust"))
                    {
                        entityfx = new EntityReddustFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, (float)p_72726_8_, (float)p_72726_10_, (float)p_72726_12_);
                    }
                    else if (p_72726_1_.equals("snowballpoof"))
                    {
                        entityfx = new EntityBreakingFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, Items.snowball);
                    }
                    else if (p_72726_1_.equals("dripWater"))
                    {
                        entityfx = new EntityDropParticleFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, Material.water);
                    }
                    else if (p_72726_1_.equals("dripLava"))
                    {
                        entityfx = new EntityDropParticleFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, Material.lava);
                    }
                    else if (p_72726_1_.equals("snowshovel"))
                    {
                        entityfx = new EntitySnowShovelFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("slime"))
                    {
                        entityfx = new EntityBreakingFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, Items.slime_ball);
                    }
                    else if (p_72726_1_.equals("heart"))
                    {
                        entityfx = new EntityHeartFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("angryVillager"))
                    {
                        entityfx = new EntityHeartFX(ALCore.instance.world(), p_72726_2_, p_72726_4_ + 0.5D, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                        ((EntityFX)entityfx).setParticleTextureIndex(81);
                        ((EntityFX)entityfx).setRBGColorF(1.0F, 1.0F, 1.0F);
                    }
                    else if (p_72726_1_.equals("happyVillager"))
                    {
                        entityfx = new EntityAuraFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                        ((EntityFX)entityfx).setParticleTextureIndex(82);
                        ((EntityFX)entityfx).setRBGColorF(1.0F, 1.0F, 1.0F);
                    }
                    else
                    {
                        int k;
                        String[] astring;

                        if (p_72726_1_.startsWith("iconcrack_"))
                        {
                            astring = p_72726_1_.split("_", 3);
                            int j = Integer.parseInt(astring[1]);

                            if (astring.length > 2)
                            {
                                k = Integer.parseInt(astring[2]);
                                entityfx = new EntityBreakingFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, Item.getItemById(j), k);
                            }
                            else
                            {
                                entityfx = new EntityBreakingFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, Item.getItemById(j), 0);
                            }
                        }
                        else
                        {
                            Block block;

                            if (p_72726_1_.startsWith("blockcrack_"))
                            {
                                astring = p_72726_1_.split("_", 3);
                                block = Block.getBlockById(Integer.parseInt(astring[1]));
                                k = Integer.parseInt(astring[2]);
                                entityfx = (new EntityDiggingFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, block, k)).applyRenderColor(k);
                            }
                            else if (p_72726_1_.startsWith("blockdust_"))
                            {
                                astring = p_72726_1_.split("_", 3);
                                block = Block.getBlockById(Integer.parseInt(astring[1]));
                                k = Integer.parseInt(astring[2]);
                                entityfx = (new EntityBlockDustFX(ALCore.instance.world(), p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, block, k)).applyRenderColor(k);
                            }
                        }
                    }

                    if (entityfx != null)
                    {
                        ALCore.instance.mc.effectRenderer.addEffect((EntityFX)entityfx);
                    }

                    return (EntityFX)entityfx;
                }
            }
        }
        else
        {
            return null;
        }
    }
}
