package com.eden.orchid.api.compilers;

import com.eden.common.json.JSONElement;
import com.eden.common.util.EdenPair;
import com.eden.orchid.api.registration.Contextual;
import com.eden.orchid.api.registration.Prioritized;

/**
 * The precompiler is a compiler to be run against files before they are sent to their appropriate OrchidCompiler. Generally,
 * this is used to extract data embedded within the file, returning the extracted data and the content after it has had
 * its embedded data removed so that the resulting content can be compiled with special options specific to that file.
 */
public interface OrchidPreCompiler extends Prioritized, Contextual {

    /**
     * Extract the data embedded within some given content, returning the data that was extracted as well as the content
     * after the embedded data has been removed.
     *
     * @param input the input content to parse
     * @return a pair representing the content after removal of embedded content, and the content that was embedded
     */
    EdenPair<String, JSONElement> getEmbeddedData(String input);

}