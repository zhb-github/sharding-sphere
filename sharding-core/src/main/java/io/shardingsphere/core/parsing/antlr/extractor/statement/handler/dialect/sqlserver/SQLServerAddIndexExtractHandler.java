/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
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
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.extractor.statement.handler.dialect.sqlserver;

import com.google.common.base.Optional;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.IndexNameExtractor;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.RuleName;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.SQLClauseExtractor;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.result.IndexExtractResult;
import io.shardingsphere.core.parsing.antlr.extractor.statement.util.ASTUtils;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * SQLServer Add index extract handler for SQLServer.
 * 
 * @author duhongjun
 */
public final class SQLServerAddIndexExtractHandler implements SQLClauseExtractor<Optional<IndexExtractResult>> {
    
    private final IndexNameExtractor indexNameExtractHandler = new IndexNameExtractor();
    
    @Override
    public Optional<IndexExtractResult> extract(final ParserRuleContext ancestorNode) {
        Optional<ParserRuleContext> indexDefOptionNode = ASTUtils.findFirstChildNode(ancestorNode, RuleName.ADD_COLUMN);
        if (!indexDefOptionNode.isPresent()) {
            return Optional.absent();
        }
        Optional<ParserRuleContext> indexNameNode = ASTUtils.findFirstChildNode(indexDefOptionNode.get(), RuleName.INDEX_NAME);
        if (!indexNameNode.isPresent()) {
            return Optional.absent();
        }
        return indexNameExtractHandler.extract(indexNameNode.get());
    }
}
