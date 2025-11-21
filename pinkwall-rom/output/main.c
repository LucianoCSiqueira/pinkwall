#include <snes.h>

// auto-generated global instructions
extern char javasnes_patterns, javasnes_patterns_end;
extern char javasnes_map, javasnes_map_end;
extern char javasnes_palette, javasnes_palette_end;
// end auto-generated global instructions

extern char bgmap0, bgmap2, bgpalette4_end, paltijolos, bgmap1, bgtiles3_end, bgmap4, bgmap3, bgmap0_end, tilesfont, tilestijolos, bgtiles4_end, palfont, bgtiles1, bgtiles0, bgtiles3, paltijolos_end, bgtiles2, bgpalette3_end, bgmap4_end, palpink_end, bgtiles4, tilespink, tilestijolos_end, bgtiles1_end, bgpalette2_end, bgmap3_end, bgpalette4, bgpalette3, bgpalette2, bgtiles0_end, bgpalette1, bgpalette0, tilespink_end, bgtiles2_end, bgpalette0_end, bgmap1_end, palpink, bgpalette1_end, bgmap2_end;
u8 mudarBG = 0;
u16 pad0 = 0;
u8 atualBG = 4;
u32 HiScore = 10000;

void printHiScore(void) {
	if ((atualBG == 4)) {
			consoleDrawText(5, 25, "Hi Score: %d", (int) HiScore);
	}
	return;
}

void limparPrintHiScore(void) {
	consoleDrawText(4, 25, "                          ");
	return;
}

void ehParaMudarBackground(void) {
	pad0 = padsCurrent(0);
	mudarBG = (pad0 & KEY_A);
	return;
}

void mudarBackground(void) {
	if ((mudarBG & mudarBG)) {
			atualBG = (atualBG >= 4) ? 0 : (atualBG + 1);
			switch (atualBG) {
				case 0:
					limparPrintHiScore();
					setScreenOff();
					WaitForVBlank();
					bgInitTileSet(1, &bgtiles0, &bgpalette0, 0, (&bgtiles0_end - &bgtiles0), (&bgpalette0_end - &bgpalette0), BG_16COLORS, 0x4000);
					bgInitMapSet(1, &bgmap0, (&bgmap0_end - &bgmap0), SC_64x64, 0x1000);
					WaitForVBlank();
					setScreenOn();
					break;
				case 1:
					limparPrintHiScore();
					setScreenOff();
					WaitForVBlank();
					bgInitTileSet(1, &bgtiles1, &bgpalette1, 0, (&bgtiles1_end - &bgtiles1), (&bgpalette1_end - &bgpalette1), BG_16COLORS, 0x4000);
					bgInitMapSet(1, &bgmap1, (&bgmap1_end - &bgmap1), SC_64x64, 0x1000);
					WaitForVBlank();
					setScreenOn();
					break;
				case 2:
					limparPrintHiScore();
					setScreenOff();
					WaitForVBlank();
					bgInitTileSet(1, &bgtiles2, &bgpalette2, 0, (&bgtiles2_end - &bgtiles2), (&bgpalette2_end - &bgpalette2), BG_16COLORS, 0x4000);
					bgInitMapSet(1, &bgmap2, (&bgmap2_end - &bgmap2), SC_64x64, 0x1000);
					WaitForVBlank();
					setScreenOn();
					break;
				case 3:
					limparPrintHiScore();
					setScreenOff();
					WaitForVBlank();
					bgInitTileSet(1, &bgtiles3, &bgpalette3, 0, (&bgtiles3_end - &bgtiles3), (&bgpalette3_end - &bgpalette3), BG_16COLORS, 0x4000);
					bgInitMapSet(1, &bgmap3, (&bgmap3_end - &bgmap3), SC_64x64, 0x1000);
					WaitForVBlank();
					setScreenOn();
					break;
				case 4:
					limparPrintHiScore();
					setScreenOff();
					WaitForVBlank();
					bgInitTileSet(1, &bgtiles4, &bgpalette4, 0, (&bgtiles4_end - &bgtiles4), (&bgpalette4_end - &bgpalette4), BG_16COLORS, 0x4000);
					bgInitMapSet(1, &bgmap4, (&bgmap4_end - &bgmap4), SC_64x64, 0x1000);
					WaitForVBlank();
					setScreenOn();
					break;
		}
	}
	return;
}

void processor(void) {
	printHiScore();
	ehParaMudarBackground();
	mudarBackground();
	return;
}

int main(void) {
	
	spcBoot();
	
	
	bgInitTileSet(0, &javasnes_patterns, &javasnes_palette, 0, (&javasnes_patterns_end - &javasnes_patterns), (&javasnes_palette_end - &javasnes_palette), BG_16COLORS, 0x4000);
	bgInitMapSet(0, &javasnes_map, (&javasnes_map_end - &javasnes_map), SC_32x32, 0x0000);
	
	
	setMode(BG_MODE1, 0);
	bgSetDisable(1);
	bgSetDisable(2);
	setScreenOn();
	
	
	WaitForVBlank();
	
	u8 i;
	for (i = 0; i < 120; i++) {
		WaitForVBlank();
	}
	dmaClearVram();
	
	dmaClearVram();
	consoleSetTextGfxPtr(0x3000);
	consoleSetTextMapPtr(0x6800);
	consoleSetTextOffset(0x0100);
	consoleInitText(1, 32, &tilesfont, &palfont);
	setScreenOff();
	bgInitTileSet(1, &bgtiles4, &bgpalette4, 0, (&bgtiles4_end - &bgtiles4), (&bgpalette4_end - &bgpalette4), BG_16COLORS, 0x4000);
	bgInitMapSet(1, &bgmap4, (&bgmap4_end - &bgmap4), SC_64x64, 0x1000);
	bgSetGfxPtr(0, 0x2000);
	bgSetMapPtr(0, 0x6800, SC_32x32);
	setMode(BG_MODE1, 0);
	bgSetDisable(2);
	setScreenOn();
	WaitForVBlank();
	oamInitGfxSet(&tilespink, (&tilespink_end - &tilespink), &palpink, (&palpink_end - &palpink), 2, (0x5000 >> 5), OBJ_SIZE32_L64);
	oamSetEx(0, OBJ_SMALL, OBJ_HIDE);
	oamInitGfxSet(&tilestijolos, (&tilestijolos_end - &tilestijolos), &paltijolos, (&paltijolos_end - &paltijolos), 3, 704, OBJ_SIZE8_L16);
	oamSetEx(1, OBJ_SMALL, OBJ_HIDE);
	oamInitGfxSet(&tilestijolos, (&tilestijolos_end - &tilestijolos), &paltijolos, (&paltijolos_end - &paltijolos), 3, 705, OBJ_SIZE8_L16);
	oamSetEx(2, OBJ_SMALL, OBJ_HIDE);
	oamInitGfxSet(&tilestijolos, (&tilestijolos_end - &tilestijolos), &paltijolos, (&paltijolos_end - &paltijolos), 3, 706, OBJ_SIZE8_L16);
	oamSetEx(3, OBJ_SMALL, OBJ_HIDE);
	oamInitGfxSet(&tilestijolos, (&tilestijolos_end - &tilestijolos), &paltijolos, (&paltijolos_end - &paltijolos), 3, 707, OBJ_SIZE8_L16);
	oamSetEx(4, OBJ_SMALL, OBJ_HIDE);
	oamInitGfxSet(&tilestijolos, (&tilestijolos_end - &tilestijolos), &paltijolos, (&paltijolos_end - &paltijolos), 3, 708, OBJ_SIZE8_L16);
	oamSetEx(5, OBJ_SMALL, OBJ_HIDE);
	oamInitGfxSet(&tilestijolos, (&tilestijolos_end - &tilestijolos), &paltijolos, (&paltijolos_end - &paltijolos), 3, 709, OBJ_SIZE8_L16);
	oamSetEx(6, OBJ_SMALL, OBJ_HIDE);
	oamInitGfxSet(&tilestijolos, (&tilestijolos_end - &tilestijolos), &paltijolos, (&paltijolos_end - &paltijolos), 3, 710, OBJ_SIZE8_L16);
	oamSetEx(7, OBJ_SMALL, OBJ_HIDE);
	oamInitGfxSet(&tilestijolos, (&tilestijolos_end - &tilestijolos), &paltijolos, (&paltijolos_end - &paltijolos), 3, 711, OBJ_SIZE8_L16);
	oamSetEx(8, OBJ_SMALL, OBJ_HIDE);
	while (1) {
		processor();
		WaitForVBlank();
	}
	return 0;
}
