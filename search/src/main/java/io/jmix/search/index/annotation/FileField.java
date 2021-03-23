/*
 * Copyright 2020 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.search.index.annotation;

import java.lang.annotation.*;

//TODO Not supported yet

/**
 * One of the field-mapping annotation. Should be set on methods in index definition interfaces.<br/>
 * Covers FileRef properties only. Allows to index file name and content.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(FileField.Container.class)
public @interface FileField {

    /**
     * Provides entity properties should be covered by this annotation.<br/>
     * Properties should be defined in a full-name format started from the root entity ("localPropertyName", "refPropertyName.propertyName").<br/>
     * Wildcard is allow on the last level of multilevel properties ("*", "refPropertyName.*").
     *
     * @return properties should be processed
     */
    String[] includeProperty() default "";

    /**
     * Provides entity properties should NOT be covered by this annotation.<br/>
     * Properties should be defined in a full-name format started from the root entity ("localPropertyName", "refPropertyName.propertyName").<br/>
     * Wildcard is allow on the last level of multilevel properties ("*", "refPropertyName.*").
     *
     * @return properties should not be processed
     */
    String[] excludeProperty() default "";

    /**
     * Defines if file content should be indexed.
     *
     * @return true if file content should be indexed (default), false if only file name should be indexed
     */
    boolean indexContent() default true;

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Container {

        FileField[] value();
    }
}
