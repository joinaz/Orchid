package com.eden.orchid.impl.tasks

import com.copperleaf.krow.TableFormatter
import com.copperleaf.krow.formatters.ascii.AsciiTableFormatter
import com.copperleaf.krow.formatters.ascii.NoBorder
import com.copperleaf.krow.krow
import com.eden.common.util.EdenUtils
import com.eden.orchid.api.OrchidContext
import com.eden.orchid.api.options.OrchidFlags
import com.eden.orchid.api.options.annotations.Description
import com.eden.orchid.api.tasks.OrchidTask
import com.eden.orchid.api.tasks.TaskService
import com.google.inject.Provider
import javax.inject.Inject

@Description("Print the Orchid help page.")
class HelpTask
@Inject
constructor(
        private val contextProvider: Provider<OrchidContext>,
        private val tasks: Provider<Set<OrchidTask>>
) : OrchidTask(10, "help", TaskService.TaskType.OTHER) {

    override fun run() {
        val formatter = AsciiTableFormatter(NoBorder())

        println(printHeader(formatter))
        println("Usage:")
        println(printUsage(formatter))
        println("Tasks:")
        println(printTasks(formatter))
        println("Options:")
        println(printOptions(formatter))
    }

    @Suppress("UNUSED_PARAMETER")
    private fun printHeader(formatter: TableFormatter<String>): String {
        return "\n\nOrchid Static Site Generator.\nVersion " + contextProvider.get().site.orchidVersion + "\n"
    }

    private fun printUsage(formatter: TableFormatter<String>): String? {
        return krow {
            showHeaders = false
            showLeaders = false

            cell("key", "value") {
                content = "  (orchid) <" + OrchidFlags.getInstance().positionalFlags.joinToString("> <") + "> [--<flag> <flag value>]"
                paddingLeft = 4
            }
            table {
                wrapTextAt = 80
            }
        }.print(formatter)
    }

    private fun printTasks(formatter: TableFormatter<String>): String? {
        return krow {
            showHeaders = false
            showLeaders = false

            for (task in tasks.get()) {
                cell("key", task.name) {
                    content = task.name
                    paddingLeft = 4
                }
                cell("description", task.name) {
                    content = task.description
                }
            }
            table {
                wrapTextAt = 80
            }

        }.print(formatter)
    }

    private fun printOptions(formatter: TableFormatter<String>): String? {
        return krow {
            showHeaders = false
            showLeaders = false

            for (flag in OrchidFlags.getInstance().describeFlags().values) {
                cell("key", flag.key) {
                    var flagText = "--" + flag.key
                    if (!EdenUtils.isEmpty(flag.aliases)) {
                        flagText += ", -" + flag.aliases.joinToString(", -")
                    }
                    content = flagText
                    paddingLeft = 4
                }
                cell("description", flag.key) {
                    content = flag.description
                }
            }
            table {
                wrapTextAt = 80
            }
        }.print(formatter)
    }

}
