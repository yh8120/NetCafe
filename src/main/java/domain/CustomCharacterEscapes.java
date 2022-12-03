package domain;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;

public class CustomCharacterEscapes extends CharacterEscapes {
	private final int[] asciiEscapes;

    public CustomCharacterEscapes()
    {
        int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
        esc['"']  = CharacterEscapes.ESCAPE_STANDARD;
        esc['\''] = CharacterEscapes.ESCAPE_STANDARD;
        esc['/']  = CharacterEscapes.ESCAPE_STANDARD;
        esc['\n'] = CharacterEscapes.ESCAPE_STANDARD;
        esc['>'] = CharacterEscapes.ESCAPE_STANDARD;
        esc['<'] = CharacterEscapes.ESCAPE_STANDARD;
        asciiEscapes = esc;
    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }

    // no further escaping (beyond ASCII chars) needed:
    @Override
    public SerializableString getEscapeSequence(int ch) {
        return null;
    }
	
}
