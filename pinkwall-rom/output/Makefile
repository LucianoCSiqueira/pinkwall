ifeq ($(strip $(PVSNESLIB_HOME)),)
$(error "Please create an environment variable PVSNESLIB_HOME by following this guide: https://github.com/alekmaul/pvsneslib/wiki/Installation")
endif

include ${PVSNESLIB_HOME}/devkitsnes/snes_rules

.PHONY: all clean logo bitmaps
export ROMNAME := PinkWall

all: logo bitmaps $(ROMNAME).sfc

sprites.pic: sprites.bmp
	$(GFXCONV) -s 32 -u 16 -o 16 -t bmp -i $<

BG_4.pic: BG_4.bmp
	$(GFXCONV) -s 8 -u 16 -o 16 -p -m -t bmp -i $<

bitmaps: fonte.pic BG_1.pic BG_2.pic BG_3.pic BG_4.pic BG_5.pic sprites.pic tijolo.pic

javasnes_logo.pic: javasnes_logo.bmp
	@echo convert javasnes_logo to map/pic/pal... $(notdir $@)
	$(GFXCONV) -s 8 -o 16 -u 16 -p -m -t bmp -i $<

clean: cleanBuildRes cleanRom cleanGfx cleanAudio

BG_2.pic: BG_2.bmp
	$(GFXCONV) -s 8 -u 16 -o 16 -p -m -t bmp -i $<

BG_5.pic: BG_5.bmp
	$(GFXCONV) -s 8 -u 16 -o 16 -p -m -t bmp -i $<

logo: javasnes_logo.pic javasnes_logo.map javasnes_logo.pal

BG_3.pic: BG_3.bmp
	$(GFXCONV) -s 8 -u 16 -o 16 -p -m -t bmp -i $<

fonte.pic: fonte.bmp
	$(GFXCONV) -s 8 -u 16 -o 16 -R -t bmp -i $<

tijolo.pic: tijolo.bmp
	$(GFXCONV) -s 8 -o 16 -u 16 -t bmp -i $<


BG_1.pic: BG_1.bmp
	$(GFXCONV) -s 8 -u 16 -o 16 -p -m -t bmp -i $<
